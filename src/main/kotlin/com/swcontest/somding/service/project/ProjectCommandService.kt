package com.swcontest.somding.service.project

import com.swcontest.somding.model.dto.request.ProjectRequestDTO

interface ProjectCommandService {
    fun createProject(projectReq: ProjectRequestDTO)

    fun deleteProject(projectId: Long)

    fun updateProject(projectReq: ProjectRequestDTO)
}