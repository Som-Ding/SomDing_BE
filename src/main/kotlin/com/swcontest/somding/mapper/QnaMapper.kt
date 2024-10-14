package com.swcontest.somding.mapper

import com.swcontest.somding.model.dto.request.CreateQuestionRequestDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.qna.Qna
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface QnaMapper {

    @Mapping(target = "questionId", ignore = true)
    @Mapping(target = "member", source = "member")
    @Mapping(target = "project", source = "project")
    @Mapping(target = "title", source = "qnaRequestDTO.title")
    @Mapping(target = "question", source = "qnaRequestDTO.question")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    fun toEntity(qnaRequestDTO: CreateQuestionRequestDTO, project: Project, member: Member): Qna
}
