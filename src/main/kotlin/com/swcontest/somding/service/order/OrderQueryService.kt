package com.swcontest.somding.service.order

import com.swcontest.somding.model.dto.response.ProjectResponseDTO

interface OrderQueryService {

    fun readMyOrder(memberId:Long): List<ProjectResponseDTO>
}