package com.swcontest.somding.service.auth

import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.model.dto.response.member.ReadProfileResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.repository.member.MemberRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberQueryServiceImpl(
        private val memberRepository: MemberRepository,
) : MemberQueryService{
    override fun readProfile(member: Member): ReadProfileResponseDTO {
        val getMember: Member = memberRepository.findById(member.memberId)
                .orElseThrow { MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }
        return ReadProfileResponseDTO( getMember.nickname, getMember.profileImg, getMember.email)
    }


}