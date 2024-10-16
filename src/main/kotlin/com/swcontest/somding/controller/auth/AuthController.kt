package com.swcontest.somding.controller.auth

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.auth.LoginRequestDTO
import com.swcontest.somding.model.dto.request.auth.SignupRequestDTO
import com.swcontest.somding.model.dto.response.auth.TokenResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.service.auth.MemberCommandService
import jakarta.servlet.http.HttpServletRequest
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
                     private val memberCommandService: MemberCommandService) {

    @PostMapping("/signup")
    fun signup(@RequestBody signupRequestDTO: SignupRequestDTO): ApiResponse<String?>{
        memberCommandService.signup(signupRequestDTO)
        return ApiResponse.onSuccess(null)

    }

    @PostMapping("/login")
    fun login(@RequestBody loginRequestDTO: LoginRequestDTO): ApiResponse<TokenResponseDTO>{
        return ApiResponse.onSuccess(memberCommandService.login(loginRequestDTO))
    }

    @GetMapping("/reissue")
    fun createRefreshToken(request: HttpServletRequest, @AuthenticationPrincipal member: Member): ApiResponse<TokenResponseDTO> {
            return ApiResponse.onSuccess( memberCommandService.reissue(request, member))
    }

}