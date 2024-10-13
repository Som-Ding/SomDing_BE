package com.swcontest.somding.model.entity.qna

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import jakarta.persistence.*


@Entity
@Table(name = "qna")
data class Qna(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        val questionId: Long = 0, // 기본값 설정

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project,

//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "order_id") // foreign key
//        var order: Order, // Order relationship


        var title: String,
        var question: String,
        var answer: String? = null,

        @Column(name = "is_private")
        var isPrivate: Boolean
) : BaseEntity(){
    constructor() : this(
            member = Member(), // Initialize with a default Member instance
            project = Project(), // Initialize with a default Project instance
            title = "",
            question = "",
            answer = null,
            isPrivate = false
    )
}
