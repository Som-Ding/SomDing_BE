package com.swcontest.somding.model.dto.response

import java.time.LocalDateTime

data class QnaResponseDTO (
        val questionId:Long,
        val title: String,
        val question: String,
        val createdAt:LocalDateTime
)