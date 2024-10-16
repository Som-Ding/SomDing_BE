package com.swcontest.somding.model.dto.request

import com.fasterxml.jackson.annotation.JsonInclude
import com.swcontest.somding.model.entity.enums.ProjectCategory
import java.time.LocalDate
import java.time.LocalDateTime


@JsonInclude(JsonInclude.Include.NON_NULL)
data class ProjectRequestDTO(
        val title: String,
        val introduce: String,
        val policy: String,
        val schedule: String,
        val category: ProjectCategory,
        val targetPrice: Int,
        val price: Int,
        val targetDate: LocalDate,
        val colorList: List<String>?,
        val sizeList: List<String>?,
        val otherList: List<String>?
)
