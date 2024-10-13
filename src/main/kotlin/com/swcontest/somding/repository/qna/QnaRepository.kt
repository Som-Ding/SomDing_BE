package com.swcontest.somding.repository.qna

import com.swcontest.somding.model.entity.qna.Qna
import org.springframework.data.jpa.repository.JpaRepository

interface QnaRepository:JpaRepository<Qna, Long>, QnaQueryRepository {
}