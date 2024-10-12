package com.swcontest.somding.service.project

import com.swcontest.somding.mapper.ProjectMapper
import com.swcontest.somding.model.dto.response.ProjectDetailImgResponseDTO
import com.swcontest.somding.model.dto.response.ProjectDetailResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.ClassifyCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.repository.project.ProjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class ProjectQueryImplService(val projectRepository: ProjectRepository,
                              private val projectMapper: ProjectMapper
) : ProjectQueryService{
    override fun readMyProject(memberId: Long): List<ProjectResponseDTO> {
        return projectRepository.getMyProjectByMemberId(1)
    }


    override fun test(memberId: Long){
//        projectRepository.findMyProjectByMemberId(2)
        projectRepository.findById(1)
    }

    override fun readProject(projectId: Long): ProjectDetailImgResponseDTO {
        return projectRepository.getProject(projectId)
    }

    override fun readProjectByCategory(category: ProjectCategory, classify: ClassifyCategory): List<ProjectResponseDTO> {
        return  projectRepository.getProjectByCategory(category, classify)
    }

}