package com.swcontest.somding.model.entity.option

import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.enums.OptionCategory
import com.swcontest.somding.model.entity.order.Order
import com.swcontest.somding.model.entity.project.Project
import jakarta.persistence.*
import org.jetbrains.annotations.NotNull

@Entity
@Table(name = "options")
data class Option(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val optionId: Long, // 기본값을 0으로 설정하여 기본 생성자에서 사용 가능

        @NotNull
        @Column(name ="option_value")
        var option: String , // 기본값을 빈 문자열로 설정

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "project_id")
        var project: Project,

        @Enumerated(EnumType.STRING)
        @NotNull
        var optionCategory:OptionCategory,

) : BaseEntity()
