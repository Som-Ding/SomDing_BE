package com.swcontest.somding.controller.auth

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.member.UpdatePasswordRequestDTO
import com.swcontest.somding.model.dto.request.member.UpdateProfileRequestDTO
import com.swcontest.somding.model.dto.response.member.ReadProfileResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.service.auth.MemberCommandService
import com.swcontest.somding.service.auth.MemberQueryService
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/member")
class MemberController(private val memberCommandService: MemberCommandService,
        private val memberQueryService: MemberQueryService) {

    @PatchMapping(value = ["/profile"], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun updateProfile(@RequestPart("updateProfileDTO") updateProfileRequestDTO: UpdateProfileRequestDTO,
                      @RequestPart("image") image: MultipartFile,
                      @AuthenticationPrincipal member: Member): ApiResponse<String?>{
        memberCommandService.updateProfile(updateProfileRequestDTO, image, member)

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