package com.swcontest.somding.model.dto.request

data class CreateQuestionRequestDTO (
        val projectId: Long,
        val title: String,
        val question: String
)