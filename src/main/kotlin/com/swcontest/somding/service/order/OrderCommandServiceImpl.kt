package com.swcontest.somding.service.order

import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.exception.order.OrderErrorCode
import com.swcontest.somding.exception.order.OrderException
import com.swcontest.somding.exception.project.ProjectErrorCode
import com.swcontest.somding.exception.project.ProjectException
import com.swcontest.somding.mapper.OrderMapper
import com.swcontest.somding.model.dto.request.CreateOrderRequestDTO
import com.swcontest.somding.repository.member.MemberRepository
import com.swcontest.somding.repository.order.OrderRepository
import com.swcontest.somding.repository.project.ProjectRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class OrderCommandServiceImpl(
private val memberRepository: MemberRepository,
        private val projectRepository: ProjectRepository,
        private val orderMapper: OrderMapper,
        private val orderRepository: OrderRepository
): OrderCommandService {
    override fun createOrder(createOrderRequestDTO: CreateOrderRequestDTO, memberId: Long) {
        val member = memberRepository.findById(1).orElseThrow(){ MemberException(MemberErrorCode.MEMBER_NOT_FOUND) }
        val project = projectRepository.findById(createOrderRequestDTO.projectId).orElseThrow{ ProjectException(ProjectErrorCode.PROJECT_NOT_FOUND) }

        val invalidOptionIds = createOrderRequestDTO.optionId.filter { optionId ->
            project.optionList.none { option -> option.optionId == optionId }
        }

        if (invalidOptionIds.isNotEmpty()) {
            throw ProjectException(ProjectErrorCode.OPTION_NOT_FOUND)
        }
        val order = orderMapper.toEntity(createOrderRequestDTO,member,project)

        orderRepository.save(order)
        orderRepository.updateSponsor(project)
    }

    override fun deleteOrder(orderId: Long, memberId: Long) {
        orderRepository.deleteByOrderIdAndMemberId(orderId, memberId)
    }

}