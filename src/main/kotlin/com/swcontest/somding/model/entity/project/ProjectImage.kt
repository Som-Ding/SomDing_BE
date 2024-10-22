package com.swcontest.somding.model.entity.project

import com.swcontest.somding.model.entity.common.BaseEntity
import jakarta.persistence.*
import lombok.Builder

@Entity
@Builder
@Table(name = "project_img")
data class ProjectImage(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val projectImageId: Long,

        var imageUrl: String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project // Project와의 관계
) : BaseEntity() {

        // Builder class
        class Builder {
                private var projectImageId: Long = 0
                private var imageUrl: String? = null
                private var project: Project? = null

                fun projectImageId(projectImageId: Long) = apply { this.projectImageId = projectImageId }
                fun imageUrl(imageUrl: String?) = apply { this.imageUrl = imageUrl }
                fun project(project: Project?) = apply { this.project = project }

                fun build(): ProjectImage {
                        return project?.let { ProjectImage(projectImageId, imageUrl, it) }
                                ?: throw IllegalArgumentException("Project must not be null")  // Ensure project is provided
                }
        }

        companion object {
                fun builder() = Builder()
        }
}
