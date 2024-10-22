package com.swcontest.somding.repository.project

import com.swcontest.somding.model.entity.project.ProjectImage
import org.springframework.data.jpa.repository.JpaRepository

interface ProjectImageRepository : JpaRepository<ProjectImage, Long>{
}