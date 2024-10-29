package com.swcontest.somding.service.scrap

import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.exception.project.ProjectErrorCode
import com.swcontest.somding.exception.project.ProjectException
import com.swcontest.somding.mapper.ScrapMapper
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.repository.member.MemberRepository
import com.swcontest.somding.repository.project.ProjectRepository
import com.swcontest.somding.repository.scrap.ScrapRepository
import org.springframework.stereotype.Service

@Service
class ScrapCommandServiceImpl
(private val projectRepository: ProjectRepository,
        private val scrapRepository: ScrapRepository,
        private val memberRepository: MemberRepository,
        private val scrapMapper: ScrapMapper): ScrapCommandService
 {
    override fun createScrap(projectId: Long, member: Member) {
        val project = projectRepository.findById(projectId).orElseThrow { ProjectException(ProjectErrorCode.PROJECT_NOT_FOUND) }
        val member = memberRepository.findById(member.memberId).orElseThrow { MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }

        scrapRepository.findByMemberAndProject(member, project).ifPresent {
            throw ProjectException(ProjectErrorCode.SCRAP_ALREADY_EXISTS)
        }
        val scrap = scrapMapper.toEntity(project,member)
        scrapRepository.save(scrap)
    }

     //삭제
     override fun deleteScrap(projectId: Long, member: Member) {

         val scrap = scrapRepository.findByMemberIdAndProjectId(member.memberId, projectId)
                 ?: throw ProjectException(ProjectErrorCode.SCRAP_NOT_FOUND)
         scrapRepository.deleteById(scrap.scrapId)
     }
}