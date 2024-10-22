package com.swcontest.somding.service.project

//import com.swcontest.somding.repository.member.MemberRepository

import com.swcontest.somding.common.config.s3.S3Manager
import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.exception.project.ProjectErrorCode
import com.swcontest.somding.exception.project.ProjectException
import com.swcontest.somding.mapper.OptionMapper
import com.swcontest.somding.mapper.ProjectMapper
import com.swcontest.somding.model.dto.request.ProjectRequestDTO
import com.swcontest.somding.model.entity.enums.OptionCategory
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.option.Option
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.project.ProjectImage
import com.swcontest.somding.repository.member.MemberRepository
import com.swcontest.somding.repository.option.OptionRepository
import com.swcontest.somding.repository.project.ProjectImageRepository
import com.swcontest.somding.repository.project.ProjectRepository
import lombok.extern.slf4j.Slf4j
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.multipart.MultipartFile
import java.util.*
import java.util.function.Function

@Service
@Transactional
@Slf4j
class ProjectCommandServiceImpl(
        private val projectRepository: ProjectRepository,
        private val memberRepository: MemberRepository,
        private val projectMapper: ProjectMapper,
        private val optionRepository: OptionRepository,
        private val optionMapper: OptionMapper,
        private val s3Manager: S3Manager,
        private val projectImageRepository: ProjectImageRepository
) : ProjectCommandService{

    override fun createProject(projectReq: ProjectRequestDTO, images: List<MultipartFile>, member: Member) {

        val member1 = memberRepository.findById(member.memberId)
                .orElseThrow { MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }

        val projectEntity = projectMapper.toEntity(projectReq, member1)
        val savedProject = projectRepository.save(projectEntity)

        // Save images
        val keyNames: MutableList<String> = mutableListOf()

        // 키 이름 생성
        for (image in images) {
            if (image != null && !image.isEmpty) {
                val uuid = UUID.randomUUID()
                keyNames.add(s3Manager.generateProjectKeyName(uuid)) // Add keys to the mutable list
            }
        }

        // S3에 파일 일괄 업로드
        val imageUrls = s3Manager.uploadFiles(keyNames, images)

        val projectImg: List<ProjectImage> = imageUrls.map { url ->
            ProjectImage.builder()
                    .imageUrl(url)
                    .project(savedProject)
                    .build()
        }

        projectImageRepository.saveAll(projectImg)

        // 옵션 저장
        saveOptions(projectEntity, projectReq)
    }


    override fun deleteProject(projectId: Long, member: Member) {
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
