package com.swcontest.somding.model.dto.request

import com.swcontest.somding.model.entity.enums.ProjectCategory

data class ProjectRequestDTO(
        var title: String,
        var introduce: String,
        var policy: String,
        var schedule: String,
        var category: ProjectCategory,
        var targetPrice: Int,
        var gatherPrice: Int,
        var price: Int,
        var sponsorNum: Int,
//        var options: List<String>
)
