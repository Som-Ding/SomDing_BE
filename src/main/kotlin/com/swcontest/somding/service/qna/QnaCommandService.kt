package com.swcontest.somding.service.qna

import com.swcontest.somding.model.dto.request.AnswerRequestDTO
import com.swcontest.somding.model.dto.request.CreateQuestionRequestDTO
import com.swcontest.somding.model.dto.request.UpdateQuestionRequestDTO

interface QnaCommandService {
    fun createQuestion(qnaRequestDTO: CreateQuestionRequestDTO)

    fun updateAnswer(answerRequestDTO: AnswerRequestDTO, memberId: Long)

    fun deleteQuestion(questionId: Long, memberId: Long)

    fun updateQuestion(questionRequestDTO: UpdateQuestionRequestDTO, memberId: Long)
}