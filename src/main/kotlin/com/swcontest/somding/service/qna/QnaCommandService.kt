package com.swcontest.somding.service.qna

import com.swcontest.somding.model.dto.request.AnswerRequestDTO
import com.swcontest.somding.model.dto.request.CreateQuestionRequestDTO
import com.swcontest.somding.model.dto.request.UpdateQuestionRequestDTO
import com.swcontest.somding.model.entity.member.Member

interface QnaCommandService {
    fun createQuestion(qnaRequestDTO: CreateQuestionRequestDTO)

    fun updateAnswer(answerRequestDTO: AnswerRequestDTO, member : Member)

    fun deleteQuestion(questionId: Long, member: Member)

    fun updateQuestion(questionRequestDTO: UpdateQuestionRequestDTO, member: Member)
}