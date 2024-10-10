package com.swcontest.somding.repository.project

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Repository

@Repository
class ProjectQueryRepositoryImpl(
        private val jpaQueryFactory: JPAQueryFactory
) : ProjectQueryRepository {
}