package com.swcontest.somding.model.dto.request

data class UpdateQuestionRequestDTO(
        var questionId: Long,
        var title: String,
        var question: String,
)
