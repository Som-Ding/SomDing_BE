package com.swcontest.somding.model.dto.request

data class CreateOrderRequestDTO(
        var projectId: Long,
        var optionId:List<Long>,
        var itemCount: Int
)
