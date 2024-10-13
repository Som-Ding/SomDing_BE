package com.swcontest.somding.service.qna

import com.swcontest.somding.model.dto.response.QnaDetailResponseDTO
import com.swcontest.somding.model.dto.response.QnaResponseDTO

interface QnaQueryService {
    fun readQna(projectId: Long):List<QnaResponseDTO>

    fun readQnaDetail(questionId: Long):QnaDetailResponseDTO
}