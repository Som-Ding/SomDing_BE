package com.swcontest.somding.controller.order

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.CreateOrderRequestDTO
import com.swcontest.somding.model.dto.response.ProjectOrderResponseDTO
import com.swcontest.somding.model.dto.response.ProjectResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.service.order.OrderCommandService
import com.swcontest.somding.service.order.OrderQueryService
import org.springframework.security.core.annotation.AuthenticationPrincipal
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
    fun createOrder(@RequestBody createOrderRequestDTO: CreateOrderRequestDTO, @AuthenticationPrincipal member: Member):ApiResponse<String?>{
        orderCommandService.createOrder(createOrderRequestDTO, member)
        return ApiResponse.onSuccess(null)
    }


    @DeleteMapping("{orderId}")
    fun deleteOrder(@PathVariable("orderId")orderId:Long, @AuthenticationPrincipal member: Member):ApiResponse<String?>{
        orderCommandService.deleteOrder(orderId, member)
        return ApiResponse.onSuccess(null)
    }


    @GetMapping("/my")
    fun readMyOrder(@AuthenticationPrincipal member: Member):ApiResponse<List<ProjectOrderResponseDTO>>{
        return ApiResponse.onSuccess(orderQueryService.readMyOrder(member))

    }
}