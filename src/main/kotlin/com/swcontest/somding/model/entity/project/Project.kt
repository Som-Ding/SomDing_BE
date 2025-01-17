package com.swcontest.somding.model.entity.project

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.enums.ProjectCategory
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.option.Option
import com.swcontest.somding.model.entity.qna.Qna
import com.swcontest.somding.model.entity.scrap.Scrap
import jakarta.persistence.*
import java.time.LocalDate

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

        var targetPrice: Int,

        var gatherPrice: Int=0,

        var price: Int,


        var sponsorNum: Int=0,

        var targetDate: LocalDate,
        @Enumerated(EnumType.STRING)
        var category: ProjectCategory,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member,

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
        var projectImgList: MutableList<ProjectImage>?,

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
        var scrapList: MutableList<Scrap>,

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
        var qnaList: MutableList<Qna>,

        @OneToMany(mappedBy = "project", cascade = [CascadeType.ALL])
        var optionList: MutableList<Option> = mutableListOf()

) : BaseEntity()
