package com.swcontest.somding.model.entity.project

import com.swcontest.somding.model.entity.common.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "project_img")
data class ProjectImage(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val projectImageId: Long,

        var imageUrl: String?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project // Project와의 관계
) : BaseEntity()
