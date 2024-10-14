package com.swcontest.somding.model.entity.order

import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.option.Option
import com.swcontest.somding.model.entity.project.Project
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val orderId: Long,

        var count: Int,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member, // Member와의 관계

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project, // Project와의 관계

        @NotNull
        var itemCount: Int,

        @ElementCollection
        @CollectionTable(name = "order_option_ids", joinColumns = [JoinColumn(name = "order_id")])
        @Column(name = "option_id")
        var optionId: MutableList<Long> = mutableListOf()


)
