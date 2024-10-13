package com.swcontest.somding.repository.scrap

import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.scrap.Scrap
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.*

interface ScrapRepository: JpaRepository<Scrap, Long>, ScrapQueryRepository {
    fun findByMemberAndProject(member: Member, project: Project): Optional<Scrap>

    @Query("SELECT s from Scrap s where s.member.memberId =:memberId AND s.project.projectId=:projectId")
    fun findByMemberIdAndProjectId(@Param("memberId") memberId:Long, @Param("projectId") projectId: Long): Scrap?
}