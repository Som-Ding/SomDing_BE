package com.swcontest.somding.service.project

import com.swcontest.somding.model.dto.request.ProjectRequestDTO
import com.swcontest.somding.model.entity.member.Member
import org.springframework.web.multipart.MultipartFile

interface ProjectCommandService {
   fun createProject(projectReq: ProjectRequestDTO,images: List<MultipartFile>, member: Member)

    fun deleteProject(projectId: Long, member: Member)

    fun updateProject(projectReq: ProjectRequestDTO)
}