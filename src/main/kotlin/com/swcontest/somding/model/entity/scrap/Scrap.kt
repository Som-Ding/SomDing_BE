//package com.swcontest.somding.model.entity.scrap
//
//import com.swcontest.somding.model.entity.common.BaseEntity
//import com.swcontest.somding.model.entity.member.Member
//import com.swcontest.somding.model.entity.project.Project
//import jakarta.persistence.*
//
//@Entity
//data class Scrap(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        val scrapId: Long = 0,
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "member_id")
//        var member: Member, // Reference to Member entity
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "project_id")
//        var project: Project // Reference to Project entity
//) : BaseEntity()
