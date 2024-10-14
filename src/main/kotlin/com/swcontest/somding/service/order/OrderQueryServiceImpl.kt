package com.swcontest.somding.service.order

import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.repository.order.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderQueryServiceImpl
(private val orderRepository: OrderRepository
        ): OrderQueryService {
    override fun readMyOrder(memberId: Long): List<ProjectResponseDTO> {
        return orderRepository.getMyOrder(memberId)
    }
}