package com.swcontest.somding.model.entity.member

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.order.Order
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.qna.Qna
import jakarta.persistence.*

@Entity
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        val memberId: Long = 0,

        var email: String,
        var loginId: String,
        var password: String,
        var nickname: String,
        var profile_img: String,
        var address: String,

        @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
        var projectList: MutableList<Project> = mutableListOf(),

        @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
        var orderList: MutableList<Order> = mutableListOf(),

        @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
        var qnaList: MutableList<Qna> = mutableListOf(),


        ) : BaseEntity()
