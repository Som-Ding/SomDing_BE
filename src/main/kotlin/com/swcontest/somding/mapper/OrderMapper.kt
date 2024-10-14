package com.swcontest.somding.mapper

import com.swcontest.somding.model.dto.request.CreateOrderRequestDTO
import com.swcontest.somding.model.dto.request.ProjectRequestDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.order.Order
import com.swcontest.somding.model.entity.project.Project
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface OrderMapper {

    @Mapping(target = "orderId", ignore = true)
    @Mapping(target = "member", source = "member")
    @Mapping(target = "project", source = "project")
    fun toEntity(createOrderRequestDTO: CreateOrderRequestDTO, member: Member, project: Project): Order

}