package com.swcontest.somding.model.entity.qna

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.jetbrains.annotations.Nullable


@Entity
@Table(name = "qna")
data class Qna(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)

        val questionId: Long , // 기본값 설정

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project,

//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "order_id") // foreign key
//        var order: Order, // Order relationship

        @NotNull
        var title: String,

        @NotNull
        var question: String,

        @Nullable
        var answer: String? = null,

        @Column(name = "is_private")
        var isPrivate: Boolean = false
) : BaseEntity()
