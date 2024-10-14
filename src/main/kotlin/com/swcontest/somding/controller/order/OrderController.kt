package com.swcontest.somding.controller.order

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.CreateOrderRequestDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.service.order.OrderCommandService
import com.swcontest.somding.service.order.OrderQueryService
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/orders")
class OrderController(
        private val orderQueryService: OrderQueryService,
        private val orderCommandService: OrderCommandService
) {

    @PostMapping("")
    fun createOrder(@RequestBody createOrderRequestDTO: CreateOrderRequestDTO):ApiResponse<String?>{
        orderCommandService.createOrder(createOrderRequestDTO, 1)
        return ApiResponse.onSuccess(null)
    }


    @DeleteMapping("{orderId}")
    fun deleteOrder(@PathVariable("orderId")orderId:Long):ApiResponse<String?>{
        orderCommandService.deleteOrder(orderId, 1)
        return ApiResponse.onSuccess(null)
    }


    @GetMapping("/my")
    fun readMyOrder():ApiResponse<List<ProjectResponseDTO>>{
        return ApiResponse.onSuccess(orderQueryService.readMyOrder(1))

    }
}