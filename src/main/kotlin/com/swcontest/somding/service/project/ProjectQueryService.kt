package com.swcontest.somding.service.project

import com.swcontest.somding.model.dto.response.ProjectDetailImgResponseDTO
import com.swcontest.somding.model.dto.response.ProjectDetailResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.ClassifyCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory

interface ProjectQueryService {
    fun readMyProject(memberId: Long): List<ProjectResponseDTO>

    fun test(memberId: Long)


    fun readProject(projectId: Long): ProjectDetailImgResponseDTO

    fun readProjectByCategory(category: ProjectCategory, classify: ClassifyCategory): List<ProjectResponseDTO>
}