package com.swcontest.somding.controller.auth

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.member.UpdatePasswordRequestDTO
import com.swcontest.somding.model.dto.request.member.UpdateProfileRequestDTO
import com.swcontest.somding.model.dto.response.member.ReadProfileResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.service.auth.MemberCommandService
import com.swcontest.somding.service.auth.MemberQueryService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/member")
class MemberController(private val memberCommandService: MemberCommandService,
        private val memberQueryService: MemberQueryService) {

    @PatchMapping("/profile")
    fun updateProfile(@RequestBody updateProfileRequestDTO: UpdateProfileRequestDTO, @AuthenticationPrincipal member: Member): ApiResponse<String?>{
        memberCommandService.updateProfile(updateProfileRequestDTO, member)

        return ApiResponse.onSuccess(null)
    }

    @GetMapping("/profile")
    fun readProfile(@AuthenticationPrincipal member: Member): ApiResponse<ReadProfileResponseDTO>{
        return ApiResponse.onSuccess(memberQueryService.readProfile(member))
    }

    @PatchMapping("/password")
    fun updatePassword(@RequestBody updatePasswordRequestDTO: UpdatePasswordRequestDTO, @AuthenticationPrincipal member: Member): ApiResponse<String?>{
        memberCommandService.updatePassword(updatePasswordRequestDTO, member)
        return ApiResponse.onSuccess(null)
    }
}