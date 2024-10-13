package com.swcontest.somding.repository.qna

import com.swcontest.somding.model.dto.request.AnswerRequestDTO
import com.swcontest.somding.model.dto.request.UpdateQuestionRequestDTO
import com.swcontest.somding.model.dto.response.QnaDetailResponseDTO
import com.swcontest.somding.model.dto.response.QnaResponseDTO

interface QnaQueryRepository {
    fun readQna(projectId:Long): List<QnaResponseDTO>

    fun updateAnswer(answerRequestDTO: AnswerRequestDTO,memberId:Long)

    fun deleteQuestion(questionId:Long, memberId: Long)

    fun readQnaDetail(questionId: Long):QnaDetailResponseDTO

    fun updateQuestion(questionRequestDTO: UpdateQuestionRequestDTO, memberId: Long)
}