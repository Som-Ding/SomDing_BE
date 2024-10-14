package com.swcontest.somding.service.project

import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.exception.project.ProjectErrorCode
import com.swcontest.somding.exception.project.ProjectException
import com.swcontest.somding.mapper.OptionMapper
import com.swcontest.somding.mapper.ProjectMapper
import com.swcontest.somding.model.dto.request.ProjectRequestDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.OptionCategory
import com.swcontest.somding.model.entity.option.Option
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.repository.member.MemberRepository
import com.swcontest.somding.repository.option.OptionRepository
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
        private val projectMapper: ProjectMapper,
        private val optionRepository: OptionRepository,
        private val optionMapper: OptionMapper
) : ProjectCommandService{

    override fun createProject(projectReq: ProjectRequestDTO) {

        val member = memberRepository.findById(1).orElseThrow(){MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }

        val projectEntity = projectMapper.toEntity(projectReq, member)
        projectRepository.save(projectEntity)

        //옵션 저장
        saveOptions(projectEntity, projectReq)
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

    private fun saveOptions(projectEntity: Project, projectReq: ProjectRequestDTO) {
        val optionsToSave = mutableListOf<Option>()

        projectReq.colorList?.forEach { color ->
            val option = optionMapper.toEntity(projectEntity, color, OptionCategory.COLOR)
            optionsToSave.add(option)
        }


        projectReq.sizeList?.forEach { size ->
            val option = optionMapper.toEntity(projectEntity, size, OptionCategory.SIZE)
            optionsToSave.add(option)
        }

        projectReq.otherList?.forEach { other ->
            val option = optionMapper.toEntity(projectEntity, other, OptionCategory.OTHER)
            optionsToSave.add(option)
        }

        if (optionsToSave.isNotEmpty()) {
            optionRepository.saveAll(optionsToSave)
        }
    }

    override fun updateProject(projectReq: ProjectRequestDTO) {
        //TODO member에 대한 확인

//        val project = projectRepository.
    }


}
