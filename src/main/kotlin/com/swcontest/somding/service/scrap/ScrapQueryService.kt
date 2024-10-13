package com.swcontest.somding.service.scrap

import com.swcontest.somding.model.dto.response.ProjectResponseDTO


interface ScrapQueryService {

    fun readMyScrap(memberId: Long): List<ProjectResponseDTO>
}