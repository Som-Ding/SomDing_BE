package com.swcontest.somding.controller

import com.swcontest.somding.model.dto.response.ProjectDetailImgResponseDTO
import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.ProjectRequestDTO

import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.ClassifyCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.service.project.ProjectCommandService
import com.swcontest.somding.service.project.ProjectQueryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/projects")
class ProjectController( private val projectCommandService: ProjectCommandService,
                         private val projectQueryService: ProjectQueryService) {

    @Operation(summary = "프로젝트 생성", description = "프로젝트를 생성합니다.")
    //TODO 사진 추가 로직 필요
    @PostMapping("")
    fun createProject(@RequestBody projectReq: ProjectRequestDTO): ApiResponse<String?> {
        projectCommandService.createProject(projectReq)
        return ApiResponse.onSuccess(null)
    }


    @GetMapping("/my")
    fun readMyProject():ApiResponse<List<ProjectResponseDTO>>{
        return ApiResponse.onSuccess(projectQueryService.readMyProject(1))
    }

    @Operation(summary = "프로젝트 삭제")
    @DeleteMapping("/{projectId}")
    fun deleteProject(@PathVariable("projectId") projectId: Long): ApiResponse<String?>{

        projectCommandService.deleteProject(projectId)
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
