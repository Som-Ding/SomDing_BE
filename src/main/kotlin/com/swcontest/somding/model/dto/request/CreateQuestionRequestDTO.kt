package com.swcontest.somding.model.dto.request

data class CreateQuestionRequestDTO (
        var projectId: Long,
        var title: String,
        var question: String,
        var answer: String? = null,
)