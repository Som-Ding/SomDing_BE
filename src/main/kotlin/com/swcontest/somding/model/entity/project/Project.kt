package com.swcontest.somding.model.entity.project

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.enum.ProjectCategory
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.option.Option
import jakarta.persistence.*
import com.swcontest.somding.model.entity.order.Order
import com.swcontest.somding.model.entity.qna.Qna

@Entity
data class Project(
        @Id
        @Column(name = "project_id")
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val projectId: Long,

        var title: String,
        var introduce: String,
        var policy: String,
        var schedule: String,
        var category: ProjectCategory,
        var targetPrice: Int,
        var gatherPrice: Int,
        var price: Int,
        var sponsorNum: Int,

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
        var optionList: MutableList<Option> = mutableListOf(),

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
        var orderList: MutableList<Order> = mutableListOf(),

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
        var projectImgList: MutableList<ProjectImage> = mutableListOf(),


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member,

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL], orphanRemoval = true)
        var qnaList: MutableList<Qna> = mutableListOf() // Qna와의 일대다 관계

) : BaseEntity()
