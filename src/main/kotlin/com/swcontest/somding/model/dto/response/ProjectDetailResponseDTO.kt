package com.swcontest.somding.model.dto.response

import com.fasterxml.jackson.annotation.JsonInclude
import com.swcontest.somding.model.entity.enums.OptionCategory
import com.swcontest.somding.model.entity.enums.ProjectCategory
import java.time.LocalDate

data class ProjectDetailResponseDTO(
        var projectId: Long,
        var title: String,
        var category: ProjectCategory? = null,
        var targetPrice: Int,
        var gatherPrice: Int,
        var targetDate: LocalDate,
        var sponsorNum: Int,
        var price: Int,
        var scrapNum: Long,
)

data class ProjectDetailImgResponseDTO(

        var projectId: Long,
        var title: String,
        var category: ProjectCategory? = null,
        var targetPrice: Int,
        var gatherPrice: Int,
        var targetDate: LocalDate,
        var sponsorNum: Int,
        var price: Int,
        var scrapNum: Long,
        var imgList: List<String>,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var colorList: List<OptionDTO>?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var sizeList: List<OptionDTO>?,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        var otherList: List<OptionDTO>?

)
data class OptionDTO(
        var optionId:Long,
        var optionCategory:OptionCategory,
        var option: String
)