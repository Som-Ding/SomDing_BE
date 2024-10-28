package com.swcontest.somding.repository.order

import com.swcontest.somding.model.dto.response.ProjectOrderResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.order.Order
import com.swcontest.somding.model.entity.project.Project

interface OrderQueryRepository {

    fun getMyOrder(memberId: Long): List<ProjectOrderResponseDTO>

    fun deleteByOrderIdAndMemberId(orderId:Long, memberId: Long)

    fun updateSponsor(project: Project)
}