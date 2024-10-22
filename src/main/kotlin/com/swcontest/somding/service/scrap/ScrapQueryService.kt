package com.swcontest.somding.service.scrap

import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.member.Member


interface ScrapQueryService {

    fun readMyScrap(member: Member): List<ProjectResponseDTO>
}