package com.swcontest.somding.repository.project

import com.swcontest.somding.model.entity.project.Project
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProjectRepository: JpaRepository<Project, Long>, ProjectQueryRepository {
}