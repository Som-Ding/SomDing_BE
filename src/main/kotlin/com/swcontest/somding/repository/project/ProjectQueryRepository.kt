package com.swcontest.somding.repository.project

import com.swcontest.somding.model.dto.response.ProjectDetailImgResponseDTO
import com.swcontest.somding.model.dto.response.ProjectDetailResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.ClassifyCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project

interface ProjectQueryRepository {
    fun getMyProjectByMemberId(memberId: Long): List<ProjectResponseDTO>
    fun getProject(projectId: Long): ProjectDetailImgResponseDTO

    fun getProjectByCategory(projectCategory: ProjectCategory, classifyCategory: ClassifyCategory): List<ProjectResponseDTO>
}