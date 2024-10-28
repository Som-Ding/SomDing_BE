package com.swcontest.somding.repository.scrap

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.project.QProject.project
import com.swcontest.somding.model.entity.project.QProjectImage.projectImage
import com.swcontest.somding.model.entity.scrap.QScrap.scrap

class ScrapQueryRepositoryImpl( private val jpaQueryFactory: JPAQueryFactory
): ScrapQueryRepository {
    override fun readMyScrap(memberId: Long): List<ProjectResponseDTO> {
        return jpaQueryFactory.select(Projections.constructor(
                ProjectResponseDTO::class.java,
                project.projectId,
                project.title,
                projectImage.imageUrl,
                project.category,
                project.targetPrice,
                project.gatherPrice,
                project.price,
                project.targetDate,
                null
        ))
                .from(project)
                .leftJoin(scrap).on(scrap.project.projectId.eq(project.projectId))
                .leftJoin(project.projectImgList, projectImage)
                .where(scrap.member.memberId.eq(memberId))
                .groupBy(
                        project.projectId, project.title, project.category, project.targetPrice, project.gatherPrice, project.price
                )
                .fetch()
    }


}