package com.swcontest.somding.model.dto.response

import java.time.LocalDateTime

data class QnaDetailResponseDTO(
        val questionId:Long,
        val title: String,
        val question: String,
        val createdAt: LocalDateTime,
        val updatedAt: LocalDateTime,
        val answer:String?
)
