package com.swcontest.somding.service.order

import com.swcontest.somding.model.dto.request.CreateOrderRequestDTO
import com.swcontest.somding.model.entity.member.Member

interface OrderCommandService {
    fun createOrder(createOrderRequestDTO: CreateOrderRequestDTO, member:Member)

    fun deleteOrder(orderId: Long, member:Member)
}