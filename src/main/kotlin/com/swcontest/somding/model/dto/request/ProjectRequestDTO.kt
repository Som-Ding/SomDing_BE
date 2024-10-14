package com.swcontest.somding.model.dto.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.swcontest.somding.model.entity.enums.ProjectCategory
import java.time.LocalDate
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProjectRequestDTO(
        var title: String,
        var introduce: String,
        var policy: String,
        var schedule: String,
        var category: ProjectCategory,
        var targetPrice: Int,
        var price: Int,
        var targetDate: LocalDate,
        var colorList: List<String>?,
        var sizeList: List<String>?,
        var otherList: List<String>?
)
