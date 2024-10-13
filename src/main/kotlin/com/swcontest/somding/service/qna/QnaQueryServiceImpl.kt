package com.swcontest.somding.service.qna

import com.swcontest.somding.model.dto.response.QnaDetailResponseDTO
import com.swcontest.somding.model.dto.response.QnaResponseDTO
import com.swcontest.somding.repository.qna.QnaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class QnaQueryServiceImpl(
        private val qnaRepository: QnaRepository,
):QnaQueryService {
    override fun readQna(projectId: Long): List<QnaResponseDTO> {
        return qnaRepository.readQna(projectId)
    }

    override fun readQnaDetail(questionId: Long): QnaDetailResponseDTO {
        return qnaRepository.readQnaDetail(questionId)
    }

}