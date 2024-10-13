package com.swcontest.somding.repository.scrap

import com.swcontest.somding.model.dto.response.ProjectResponseDTO

interface ScrapQueryRepository {
    fun readMyScrap(memberId: Long): List<ProjectResponseDTO>
}