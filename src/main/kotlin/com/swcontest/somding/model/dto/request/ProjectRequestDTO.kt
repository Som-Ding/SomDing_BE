package com.swcontest.somding.model.dto.request

import com.swcontest.somding.model.entity.enums.ProjectCategory
import java.time.LocalDate
import java.time.LocalDateTime

data class ProjectRequestDTO(
        var title: String,
        var introduce: String,
        var policy: String,
        var schedule: String,
        var category: ProjectCategory,
        var targetPrice: Int,
        var gatherPrice: Int,
        var price: Int,
        var sponsorNum: Int =0,
        var targetDate: LocalDate
//        var options: List<String>
)
