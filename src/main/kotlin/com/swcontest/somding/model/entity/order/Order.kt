package com.swcontest.somding.model.entity.order

import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.option.Option
import com.swcontest.somding.model.entity.qna.Qna
import com.swcontest.somding.model.entity.review.Review
import jakarta.persistence.*

@Entity
@Table(name = "orders")
data class Order(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val orderId: Long = 0, // 기본값 0으로 설정

        var count: Int = 0, // 기본값 0으로 설정

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member, // Member와의 관계

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project, // Project와의 관계

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "option_id")
        var option: Option, //

        @OneToOne(mappedBy = "order", fetch = FetchType.LAZY)
        var review: Review, // 기본값 null로 설정

        @OneToMany(mappedBy = "order", cascade = [CascadeType.ALL], orphanRemoval = true)
        var qnaList: MutableList<Qna> = mutableListOf()

)
