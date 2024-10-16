package com.swcontest.somding.model.dto.request

data class UpdateQuestionRequestDTO(
        val questionId: Long,
        val title: String,
        val question: String,
)
