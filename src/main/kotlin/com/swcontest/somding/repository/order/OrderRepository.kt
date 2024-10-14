package com.swcontest.somding.repository.order

import com.swcontest.somding.model.entity.order.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface OrderRepository:JpaRepository<Order,Long>, OrderQueryRepository {
}