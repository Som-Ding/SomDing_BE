package com.swcontest.somding.controller.scrap

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.service.scrap.ScrapCommandService
import com.swcontest.somding.service.scrap.ScrapQueryService
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/projects/scrap/")
class ScrapController(private val scrapQueryService: ScrapQueryService, private val scrapCommandService: ScrapCommandService) {

    @GetMapping("{projectId}")
    fun createScrap(@PathVariable("projectId")projectId: Long):ApiResponse<String?>{
        scrapCommandService.createScrap(projectId)
        return ApiResponse.onSuccess(null)
    }

    @DeleteMapping("{projectId}")
    fun deleteScrap(@PathVariable("projectId")projectId: Long):ApiResponse<String?>{
        scrapCommandService.deleteScrap(projectId)
        return ApiResponse.onSuccess(null)
    }

    @GetMapping("/my")
    fun readMyScrap(@AuthenticationPrincipal member: Member):ApiResponse<List<ProjectResponseDTO>>{
        return ApiResponse.onSuccess(scrapQueryService.readMyScrap(member))
    }


}