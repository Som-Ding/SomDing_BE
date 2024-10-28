package com.swcontest.somding.repository.order

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.swcontest.somding.exception.order.OrderErrorCode
import com.swcontest.somding.exception.order.OrderException
import com.swcontest.somding.model.dto.response.ProjectOrderResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.order.QOrder.order
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.project.QProject.project
import com.swcontest.somding.model.entity.project.QProjectImage.projectImage
import org.springframework.stereotype.Repository

@Repository
class OrderQueryRepositoryImpl( private val jpaQueryFactory: JPAQueryFactory): OrderQueryRepository {
    override fun getMyOrder(memberId: Long): List<ProjectOrderResponseDTO> {
        return jpaQueryFactory
                .selectDistinct(Projections.constructor(
                        ProjectOrderResponseDTO::class.java,
                        project.projectId,
                        project.title,
                        projectImage.imageUrl,
                        project.category,
                        project.targetPrice,
                        project.gatherPrice,
                        project.price,
                        project.targetDate,
                        order.orderId
                ))
                .from(order)
                .join(order.project, project)
                .leftJoin(project.projectImgList, projectImage)
                .where(order.member.memberId.eq(memberId))
                .groupBy(project.projectId, project.title, project.category, project.targetPrice, project.gatherPrice, project.price,
                        order.orderId)
                .fetch()
    }

    override fun deleteByOrderIdAndMemberId(orderId:Long, memberId: Long){
        val projectId = jpaQueryFactory
                .select(order.project.projectId)
                .from(order)
                .where(order.orderId.eq(orderId)
                        .and(order.member.memberId.eq(memberId)))
                .fetchOne()
                ?: throw OrderException(OrderErrorCode.ORDER_NOT_FOUND)

        val deleteCount = jpaQueryFactory.delete(order)
                .where(order.orderId.eq(orderId)
                        .and(order.member.memberId.eq(memberId)))
                .execute()

        if (deleteCount == 0L) {
            throw OrderException(OrderErrorCode.ORDER_NOT_FOUND)
        }
        jpaQueryFactory.update(project)
                .set(project.sponsorNum, project.sponsorNum.subtract(1))
                .set(project.gatherPrice, project.gatherPrice.subtract(project.price))
                .where(project.projectId.eq(projectId))
                .execute()

    }

    override fun updateSponsor(projectEntity: Project) {
        jpaQueryFactory.update(project)
                .set(project.sponsorNum, project.sponsorNum.add(1))
                .set(project.gatherPrice, project.gatherPrice.add(project.price))
                .where(project.projectId.eq(projectEntity.projectId))
                .execute()
    }


}