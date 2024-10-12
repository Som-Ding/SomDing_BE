//package com.swcontest.somding.model.entity.review
//
//import com.swcontest.somding.model.entity.common.BaseEntity
//import jakarta.persistence.*
//
//@Entity
//@Table(name = "review_img")
//data class ReviewImage(
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        val reviewImgId: Long = 0,
//
//        var imageUrl: String, // Change to String for URL
//
//        @ManyToOne(fetch = FetchType.LAZY)
//        @JoinColumn(name = "review_id")
//        var review: Review // Reference to Review entity
//) : BaseEntity()
