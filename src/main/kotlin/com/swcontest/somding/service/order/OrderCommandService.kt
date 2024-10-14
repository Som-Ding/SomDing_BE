package com.swcontest.somding.service.order

import com.swcontest.somding.model.dto.request.CreateOrderRequestDTO

interface OrderCommandService {
    fun createOrder(createOrderRequestDTO: CreateOrderRequestDTO, memberId:Long)

    fun deleteOrder(orderId: Long, memberId:Long)
}