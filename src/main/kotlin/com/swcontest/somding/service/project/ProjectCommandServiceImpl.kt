package com.swcontest.somding.service.project

import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.exception.project.ProjectErrorCode
import com.swcontest.somding.exception.project.ProjectException
import com.swcontest.somding.mapper.ProjectMapper
import com.swcontest.somding.model.dto.request.ProjectRequestDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.repository.member.MemberRepository
import com.swcontest.somding.repository.project.ProjectRepository
//import com.swcontest.somding.repository.member.MemberRepository
import lombok.extern.slf4j.Slf4j

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.jvm.optionals.getOrElse
import kotlin.math.log

@Service
@Transactional
@Slf4j
class ProjectCommandServiceImpl(
        private val projectRepository: ProjectRepository,
        private val memberRepository: MemberRepository,
        private val projectMapper: ProjectMapper
) : ProjectCommandService{

    override fun createProject(projectReq: ProjectRequestDTO) {

        val member = memberRepository.findById(1).orElseThrow(){MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }

        val projectEntity = projectMapper.toEntity(projectReq, member)
        projectRepository.save(projectEntity)

    }

    override fun deleteProject(projectId: Long) {
        //TODO member에 대한 확인
        //TODO option도 지워야함
        // 해당 ID로 프로젝트를 찾기
        val project = projectRepository.findById(projectId).orElseThrow(){ProjectException(ProjectErrorCode.PROJECT_NOT_FOUND) }

        if(project.sponsorNum >0) {
            throw  ProjectException(ProjectErrorCode.PROJECT_DELETED_FAILED)
        }else{
            projectRepository.deleteById(projectId)
        }
    }

    override fun updateProject(projectReq: ProjectRequestDTO) {
        //TODO member에 대한 확인

//        val project = projectRepository.
    }


}
