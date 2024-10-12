package com.swcontest.somding.model.dto.response

import com.swcontest.somding.model.entity.enums.ProjectCategory // Import your enum class for ProjectCategory
import java.time.LocalDate

data class ProjectResponseDTO(
        var projectId: Long,
        var title: String,
        var img: String?,
        var category: ProjectCategory,
        var targetPrice: Int,
        var gatherPrice: Int,
        var price: Int
)

