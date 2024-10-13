package com.swcontest.somding.repository.qna

import com.querydsl.core.types.Projections
import com.querydsl.jpa.impl.JPAQueryFactory
import com.swcontest.somding.exception.qna.QnaErrorCode
import com.swcontest.somding.exception.qna.QnaException
import com.swcontest.somding.model.dto.request.AnswerRequestDTO
import com.swcontest.somding.model.dto.request.UpdateQuestionRequestDTO
import com.swcontest.somding.model.dto.response.QnaDetailResponseDTO
import com.swcontest.somding.model.dto.response.QnaResponseDTO
import com.swcontest.somding.model.entity.qna.QQna.qna

class QnaQueryRepositoryImpl
(private val jpaQueryFactory: JPAQueryFactory): QnaQueryRepository {

    override fun readQna(projectId:Long): List<QnaResponseDTO>{
          return jpaQueryFactory.select(Projections.constructor(
                QnaResponseDTO::class.java,
                qna.questionId,
                qna.title,
                qna.question,
                qna.createdAt
        ))
                .from(qna)
                .where(qna.project.projectId.eq(projectId))
                .fetch()
    }

    override fun updateAnswer(answerRequestDTO: AnswerRequestDTO, memberId: Long) {
        val updatedCount = jpaQueryFactory.update(qna)
                .set(qna.answer, answerRequestDTO.answer)
                .where(qna.questionId.eq(answerRequestDTO.questionId)
                        .and(qna.project.member.memberId.eq(memberId)))
                .execute()

        if (updatedCount.toInt() == 0) {
            throw QnaException(QnaErrorCode.QUESTION_NOT_FOUND)
        }

    }

    override fun deleteQuestion(questionId: Long, memberId: Long) {
        val deleteCount = jpaQueryFactory.delete(qna)
                .where(qna.questionId.eq(questionId)
                        .and(qna.member.memberId.eq(memberId)))
                .execute()
        if (deleteCount.toInt() == 0) {
            throw QnaException(QnaErrorCode.QUESTION_NOT_FOUND)
        }
    }

    override fun readQnaDetail(questionId: Long): QnaDetailResponseDTO {
        val qnaDetail = jpaQueryFactory.select(Projections.constructor(
                QnaDetailResponseDTO::class.java,
                qna.questionId,
                qna.title,
                qna.question,
                qna.createdAt,
                qna.updatedAt,
                qna.answer
        ))
                .from(qna)
                .where(qna.questionId.eq(questionId))
                .fetchOne() ?: throw QnaException(QnaErrorCode.QUESTION_NOT_FOUND)

        return qnaDetail
    }

    override fun updateQuestion(questionRequestDTO: UpdateQuestionRequestDTO, memberId: Long) {
        val updatedCount = jpaQueryFactory.update(qna)
                .set(qna.title, questionRequestDTO.title)
                .set(qna.question, questionRequestDTO.question)
                .where(qna.questionId.eq(questionRequestDTO.questionId)
                        .and(qna.member.memberId.eq(memberId))
                        .and(qna.answer.isNull().or(qna.answer.eq(""))))// answer 필드가 null일 때만 업데이트 허용
                .execute()

        if (updatedCount == 0L) {
            throw QnaException(QnaErrorCode.QUESTION_NOT_FOUND) // 업데이트된 항목이 없으면 예외 발생
        }
    }


}