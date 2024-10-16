package com.swcontest.somding.model.dto.request

data class CreateOrderRequestDTO(
        val projectId: Long,
        val optionId:List<Long>,
        val itemCount: Int
)
