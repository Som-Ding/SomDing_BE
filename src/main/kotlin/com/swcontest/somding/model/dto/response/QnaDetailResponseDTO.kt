package com.swcontest.somding.model.dto.response

import java.time.LocalDateTime

data class QnaDetailResponseDTO(
        val questionId:Long,
        var title: String,
        var question: String,
        var createdAt: LocalDateTime,
        var updatedAt: LocalDateTime,
        var answer:String?
)
