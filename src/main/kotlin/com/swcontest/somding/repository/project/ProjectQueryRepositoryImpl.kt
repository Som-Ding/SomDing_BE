package com.swcontest.somding.repository.project

import com.querydsl.core.types.Projections
import com.querydsl.jpa.JPAExpressions
import com.querydsl.jpa.impl.JPAQueryFactory
import com.swcontest.somding.exception.project.ProjectErrorCode
import com.swcontest.somding.exception.project.ProjectException
import com.swcontest.somding.model.dto.response.OptionDTO
import com.swcontest.somding.model.dto.response.ProjectDetailImgResponseDTO
import com.swcontest.somding.model.dto.response.ProjectDetailResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.enums.ClassifyCategory
import com.swcontest.somding.model.entity.enums.OptionCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.model.entity.project.QProject.project
import com.swcontest.somding.model.entity.project.QProjectImage.projectImage
import com.swcontest.somding.model.entity.scrap.QScrap.scrap
import com.swcontest.somding.model.entity.option.QOption.option1
import org.springframework.stereotype.Repository

@Repository
class ProjectQueryRepositoryImpl(
        private val jpaQueryFactory: JPAQueryFactory
) : ProjectQueryRepository {

    override fun getMyProjectByMemberId(memberId: Long): List<ProjectResponseDTO> {
        return jpaQueryFactory.select(Projections.constructor(
                ProjectResponseDTO::class.java,
                project.projectId,
                project.title,
                projectImage.imageUrl,
                project.category,
                project.targetPrice,
                project.gatherPrice,
                project.price
        ))
                .from(project)
                .leftJoin(project.projectImgList, projectImage)
                .where(project.member.memberId.eq(memberId))
                .groupBy(project.projectId, project.title, project.category, project.targetPrice, project.gatherPrice, project.price)
                .fetch()
    }


    override fun getProject(projectId: Long): ProjectDetailImgResponseDTO {
        val scrapCountSubquery = JPAExpressions
                .select(scrap.count())
                .from(scrap)
                .where(scrap.project.projectId.eq(projectId))

        val projectDetail = jpaQueryFactory
                .select(
                        Projections.constructor(
                                ProjectDetailResponseDTO::class.java,
                                project.projectId,
                                project.title,
                                project.category,
                                project.targetPrice,
                                project.gatherPrice,
                                project.targetDate,
                                project.sponsorNum,
                                project.price,
                                scrapCountSubquery // 서브쿼리 사용
                        )
                )
                .from(project)
                .leftJoin(project.scrapList, scrap)
                .where(project.projectId.eq(projectId))
                .groupBy(
                        project.projectId,
                        project.title,
                        project.category,
                        project.targetPrice,
                        project.gatherPrice,
                        project.targetDate,
                        project.sponsorNum,
                        project.price
                )
                .fetchOne() ?: throw ProjectException(ProjectErrorCode.PROJECT_NOT_FOUND)


        val imgList: List<String> = jpaQueryFactory
                .select(projectImage.imageUrl)
                .from(projectImage)
                .where(projectImage.project.projectId.eq(projectId))
                .fetch()

        val options = jpaQueryFactory
                .select(Projections.constructor(OptionDTO::class.java,
                        option1.optionId,
                        option1.optionCategory,
                        option1.option))
                .from(option1)
                .where(option1.project.projectId.eq(projectId))
                .fetch()


        val colorList = options.filter { it.optionCategory == OptionCategory.COLOR }
        val sizeList = options.filter { it.optionCategory == OptionCategory.SIZE }
        val otherList = options.filter { it.optionCategory == OptionCategory.OTHER }

        return ProjectDetailImgResponseDTO(
                projectId = projectDetail.projectId,
                title = projectDetail.title,
                category = projectDetail.category,
                targetPrice = projectDetail.targetPrice,
                gatherPrice = projectDetail.gatherPrice,
                targetDate = projectDetail.targetDate,
                sponsorNum = projectDetail.sponsorNum,
                price = projectDetail.price,
                scrapNum = projectDetail.scrapNum,
                imgList = imgList,
                colorList = colorList,
                sizeList = sizeList,
                otherList = otherList
        )
    }


    override fun getProjectByCategory(category: ProjectCategory, classify: ClassifyCategory): List<ProjectResponseDTO> {
        val query = jpaQueryFactory.select(Projections.constructor(
                ProjectResponseDTO::class.java,
                project.projectId,
                project.title,
                projectImage.imageUrl,
                project.category,
                project.targetPrice,
                project.gatherPrice,
                project.price
        ))
                .from(project)
                .leftJoin(project.projectImgList, projectImage)

        if (category != ProjectCategory.ALL) {
            query.where(project.category.eq(category))
        }

        query.groupBy(
                project.projectId,
                project.title,
                project.category,
                project.targetPrice,
                project.gatherPrice,
                project.price
        )

        when (classify) {
            ClassifyCategory.LATEST -> query.orderBy(project.createdAt.desc())
            ClassifyCategory.MOST_SPONSORED -> query.orderBy(project.sponsorNum.desc())
            ClassifyCategory.HIGHEST_AMOUNT -> query.orderBy(project.gatherPrice.desc())
            ClassifyCategory.CLOSING_SOON -> query.orderBy(project.targetDate.asc())
            ClassifyCategory.POPULARITY -> {
                query.leftJoin(project.scrapList, scrap)
                query.orderBy(scrap.count().desc())
            }
        }

        return query.fetch()
    }




}
