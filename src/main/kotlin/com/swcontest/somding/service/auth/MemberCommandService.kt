package com.swcontest.somding.service.auth

import com.swcontest.somding.model.dto.request.auth.LoginRequestDTO
import com.swcontest.somding.model.dto.request.auth.SignupRequestDTO
import com.swcontest.somding.model.dto.request.member.UpdatePasswordRequestDTO
import com.swcontest.somding.model.dto.request.member.UpdateProfileRequestDTO
import com.swcontest.somding.model.dto.response.auth.TokenResponseDTO
import com.swcontest.somding.model.entity.member.Member
import jakarta.servlet.http.HttpServletRequest

interface MemberCommandService {

    fun signup(signupRequestDTO: SignupRequestDTO)
    fun login(loginRequestDTO: LoginRequestDTO): TokenResponseDTO

    fun reissue(request: HttpServletRequest, member:Member): TokenResponseDTO

    fun updateProfile(updateProfileRequestDTO: UpdateProfileRequestDTO, member: Member)

    fun updatePassword(updatePasswordDTO: UpdatePasswordRequestDTO, member: Member)

}