package com.swcontest.somding.controller.project

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.ProjectRequestDTO
import com.swcontest.somding.model.dto.response.ProjectDetailImgResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.ClassifyCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.service.project.ProjectCommandService
import com.swcontest.somding.service.project.ProjectQueryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.http.MediaType
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@RestController
@RequestMapping("/api/projects")
class ProjectController( private val projectCommandService: ProjectCommandService,
                         private val projectQueryService: ProjectQueryService) {

    @Operation(summary = "프로젝트 생성", description = "프로젝트를 생성합니다.")
    @PostMapping(value = [""], consumes = [MediaType.MULTIPART_FORM_DATA_VALUE], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createProject(
            @RequestPart("projectReq") projectReq: ProjectRequestDTO,
            @RequestPart("images") images: List<MultipartFile>,
            @AuthenticationPrincipal member: Member
    ): ApiResponse<String?> {
        projectCommandService.createProject(projectReq, images, member)
        return ApiResponse.onSuccess(null)
    }




    @GetMapping("/my")
    fun readMyProject( @AuthenticationPrincipal member: Member):ApiResponse<List<ProjectResponseDTO>>{
        return ApiResponse.onSuccess(projectQueryService.readMyProject(member))
    }

    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{projectId}")
    fun deleteProject(@PathVariable("projectId") projectId: Long, member: Member): ApiResponse<String?>{

        projectCommandService.deleteProject(projectId, member)
        return ApiResponse.onSuccess(null)
    }

    @Operation(summary = "프로젝트 상세조회")
    @GetMapping("/{projectId}")
    fun readProject(@PathVariable("projectId") projectId: Long):ApiResponse<ProjectDetailImgResponseDTO>{
        return ApiResponse.onSuccess(projectQueryService.readProject(projectId))
    }

    @Operation(summary = "프로젝트 카테고리별 조회")
    @GetMapping("")
    fun readAllProject(
            @RequestParam("category", required = true) category: ProjectCategory,
            @RequestParam("sort", required = true) sortCriteria: ClassifyCategory
    ): ApiResponse<List<ProjectResponseDTO>> {
        return ApiResponse.onSuccess( projectQueryService.readProjectByCategory(category, sortCriteria))
    }


}
