package com.swcontest.somding.service.scrap

import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.repository.scrap.ScrapRepository
import org.springframework.stereotype.Service

@Service
class ScrapQueryServiceImpl
(private val scrapRepository: ScrapRepository)
    : ScrapQueryService {
    override fun readMyScrap(memberId: Long): List<ProjectResponseDTO> {
        return scrapRepository.readMyScrap(memberId)
    }


}