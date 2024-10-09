package com.swcontest.somding.model.entity.review

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.order.Order
import jakarta.persistence.*

@Entity
data class Review(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val reviewId: Long = 0,

        @OneToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "order_id")
        var order: Order, // Reference to Order entity

        var title: String,
        var content: String,
        var rate: Float,

        @OneToMany(mappedBy = "review", cascade = [CascadeType.ALL])
        var reviewImgList: MutableList<ReviewImage> = mutableListOf()
) : BaseEntity()
