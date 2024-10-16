package com.swcontest.somding.model.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.swcontest.somding.model.entity.enums.OptionCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import java.time.LocalDate

data class ProjectDetailResponseDTO(
        val projectId: Long,
        val title: String,
        val category: ProjectCategory? = null,
        val targetPrice: Int,
        val gatherPrice: Int,
        val targetDate: LocalDate,
        val sponsorNum: Int,
        val price: Int,
        val scrapNum: Long,
)

data class ProjectDetailImgResponseDTO(

        val projectId: Long,
        val title: String,
        val category: ProjectCategory? = null,
        val targetPrice: Int,
        val gatherPrice: Int,
        val targetDate: LocalDate,
        val sponsorNum: Int,
        val price: Int,
        val scrapNum: Long,
        val imgList: List<String>,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val colorList: List<OptionDTO>?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val sizeList: List<OptionDTO>?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val otherList: List<OptionDTO>?

)
data class OptionDTO(
        val optionId:Long,
        val optionCategory:OptionCategory,
        val option: String
)