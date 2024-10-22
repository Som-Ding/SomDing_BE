package com.swcontest.somding.service.order

import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.member.Member

interface OrderQueryService {

    fun readMyOrder(member: Member): List<ProjectResponseDTO>
}