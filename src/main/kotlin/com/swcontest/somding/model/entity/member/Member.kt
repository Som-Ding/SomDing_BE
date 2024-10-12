package com.swcontest.somding.model.entity.member

import com.fasterxml.jackson.annotation.JsonManagedReference
import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.project.Project
import jakarta.persistence.*
import lombok.ToString

@Entity
@ToString(exclude = arrayOf("member"))
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        val memberId: Long,

        var email: String,
        var loginId: String,
        var password: String,
        var nickname: String,
        var profile_img: String,
        var address: String,

        @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
        var projectList: MutableList<Project> = mutableListOf()

) : BaseEntity() {
    // 기본 생성자
    constructor() : this(
            0L,
            "",
            "",
            "",
            "",
            "",
            "",
            mutableListOf()
    )
}