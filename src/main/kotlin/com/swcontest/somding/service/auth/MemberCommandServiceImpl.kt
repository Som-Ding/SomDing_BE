package com.swcontest.somding.service.auth

import com.swcontest.somding.common.apiPayload.code.GeneralErrorCode
import com.swcontest.somding.common.config.s3.S3Manager
import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.jwt.JwtManager
import com.swcontest.somding.mapper.MemberMapper
import com.swcontest.somding.model.dto.request.auth.LoginRequestDTO
import com.swcontest.somding.model.dto.request.auth.SignupRequestDTO
import com.swcontest.somding.model.dto.request.member.UpdatePasswordRequestDTO
import com.swcontest.somding.model.dto.request.member.UpdateProfileRequestDTO
import com.swcontest.somding.model.dto.response.auth.TokenResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.ProjectImage
import com.swcontest.somding.repository.member.MemberRepository
import jakarta.servlet.http.HttpServletRequest
import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.util.StringUtils
import org.springframework.web.multipart.MultipartFile
import java.util.*

@Service
@Transactional
class MemberCommandServiceImpl(
        private val memberRepository: MemberRepository,
        private val memberMapper: MemberMapper,
        private val passwordEncoder: PasswordEncoder,
        private val jwtManager: JwtManager,
        private val s3Manager: S3Manager,
): MemberCommandService {
    private val logger = LoggerFactory.getLogger(MemberCommandServiceImpl::class.java)

    override fun signup(signupRequestDTO: SignupRequestDTO) {
        if (memberRepository.findByEmail(signupRequestDTO.email) != null) {
            throw MemberException(MemberErrorCode.MEMBER_ALREADY_EXIST)
        }

        val password: String = signupRequestDTO.password
        val encodedPw = passwordEncoder.encode(password)

        val member = memberMapper.toEntity(signupRequestDTO, encodedPw)
        memberRepository.save(member)
    }

    override fun login(loginRequestDTO: LoginRequestDTO): TokenResponseDTO {

        //비밀번호 비번
        val member: Member = memberRepository.findByEmail(loginRequestDTO.email)
                ?: throw MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        val checkPw = passwordEncoder.matches(loginRequestDTO.password, member.password)
        if (checkPw) {
            val  refreshToken = jwtManager.generateRefreshToken(member.memberId) // 토큰 발급
            val  accessToken = jwtManager.generateAccessToken(member.memberId)
            member.updateRefreshToken(refreshToken)
            return  TokenResponseDTO(accessToken, refreshToken)
        } else {
            throw MemberException(MemberErrorCode.PASSWORD_NOT_MATCH)
        }
    }

    override fun reissue(request: HttpServletRequest, member: Member): TokenResponseDTO {
        val accessToken = request.getHeader("Authorization")
        val refreshToken = request.getHeader("Refresh-Token")

        if (StringUtils.hasText(accessToken) && accessToken.startsWith("Bearer ") && StringUtils.hasText(refreshToken) && refreshToken.startsWith("Bearer ")) {
            val  refreshToken = jwtManager.generateRefreshToken(member.memberId)
            val  accessToken = jwtManager.generateAccessToken(member.memberId)
            member.updateRefreshToken(refreshToken)
            return  TokenResponseDTO(accessToken, refreshToken)
        }
        throw MemberException(GeneralErrorCode.UNAUTHORIZED_401)
    }

    override fun updateProfile(updateProfileRequestDTO: UpdateProfileRequestDTO, image: MultipartFile, member: Member) {
        val getMember: Member = memberRepository.findById(member.memberId)
                .orElseThrow { MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }

        getMember.profileImg?.let { s3Manager.deleteFile(it) }

        var imageUrl: String?

        if (image != null && !image.isEmpty) {
            val uuid = UUID.randomUUID()
            val keyNames = s3Manager.generateProfileKeyName(uuid)
            imageUrl = s3Manager.uploadFile(keyNames, image)
        }
        else{
            imageUrl=null
        }
        getMember.updateProfile(updateProfileRequestDTO, imageUrl)

    }

    override fun updatePassword(updatePasswordDTO: UpdatePasswordRequestDTO, member: Member) {
        val getMember: Member = memberRepository.findById(member.memberId)
                .orElseThrow { MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }

        val encodedPw = passwordEncoder.encode(updatePasswordDTO.password)
        val checkPw = passwordEncoder.matches(encodedPw, member.password)
        if (checkPw) {
            throw MemberException(MemberErrorCode.PASSWORD_UPDATE_FAILED)
        } else {
            getMember.updatePassword(encodedPw)
        }
    }
}