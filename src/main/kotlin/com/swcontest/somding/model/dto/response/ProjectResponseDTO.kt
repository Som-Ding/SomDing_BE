package com.swcontest.somding.model.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.swcontest.somding.model.entity.enums.ProjectCategory // Import your enum class for ProjectCategory
import lombok.AllArgsConstructor
import java.time.LocalDate


@AllArgsConstructor
data class ProjectResponseDTO(
        val projectId: Long,
        val title: String,
        val img: String?,
        val category: ProjectCategory,
        val targetPrice: Int,
        val gatherPrice: Int,
        val price: Int,
        val targetDate: LocalDate
)

data class ProjectOrderResponseDTO(
        val projectId: Long,
        val title: String,
        val img: String?,
        val category: ProjectCategory,
        val targetPrice: Int,
        val gatherPrice: Int,
        val price: Int,
        val targetDate: LocalDate,
        val orderId: Long
)

