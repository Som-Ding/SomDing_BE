package com.swcontest.somding.model.entity.scrap

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import jakarta.persistence.*
import java.time.LocalDate

@Entity
data class Scrap(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val scrapId: Long,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project
) : BaseEntity(){
        constructor() : this(
                0L,
                Member(),
                Project(),
        )
}

