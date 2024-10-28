package com.swcontest.somding.service.project

import com.swcontest.somding.mapper.ProjectMapper
import com.swcontest.somding.model.dto.response.ProjectDetailImgResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.ClassifyCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.repository.project.ProjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProjectQueryServiceImpl(val projectRepository: ProjectRepository
) : ProjectQueryService{
    override fun readMyProject(member: Member): List<ProjectResponseDTO> {
        return projectRepository.getMyProjectByMemberId(member.memberId)
    }


    override fun readProject(projectId: Long): ProjectDetailImgResponseDTO {
        return projectRepository.getProject(projectId)
    }

    override fun readProjectByCategory(category: ProjectCategory, classify: ClassifyCategory): List<ProjectResponseDTO> {
        return  projectRepository.getProjectByCategory(category, classify)
    }

}