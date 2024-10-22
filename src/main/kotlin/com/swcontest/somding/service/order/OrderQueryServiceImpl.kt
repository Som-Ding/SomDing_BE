package com.swcontest.somding.service.order

import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.repository.order.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderQueryServiceImpl
(private val orderRepository: OrderRepository
        ): OrderQueryService {
    override fun readMyOrder(member:Member): List<ProjectResponseDTO> {
        return orderRepository.getMyOrder(member.memberId)
    }
}