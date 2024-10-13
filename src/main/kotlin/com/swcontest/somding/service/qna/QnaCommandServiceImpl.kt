package com.swcontest.somding.service.qna

import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.exception.project.ProjectErrorCode
import com.swcontest.somding.exception.project.ProjectException
import com.swcontest.somding.mapper.QnaMapper
import com.swcontest.somding.model.dto.request.AnswerRequestDTO
import com.swcontest.somding.model.dto.request.CreateQuestionRequestDTO
import com.swcontest.somding.model.dto.request.UpdateQuestionRequestDTO
import com.swcontest.somding.repository.member.MemberRepository
import com.swcontest.somding.repository.project.ProjectRepository
import com.swcontest.somding.repository.qna.QnaRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class QnaCommandServiceImpl(
        private val qnaRepository: QnaRepository,
        private val memberRepository: MemberRepository,
        private val projectRepository: ProjectRepository,
        private val qnaMapper: QnaMapper
): QnaCommandService {

    override fun createQuestion(qnaRequestDTO: CreateQuestionRequestDTO){
        //memberId
        val member =memberRepository.findById(1).orElseThrow{(MemberException(MemberErrorCode.MEMBER_NOT_FOUND))}
        //projectId
        val project = projectRepository.findById(qnaRequestDTO.projectId).orElseThrow{ProjectException(ProjectErrorCode.PROJECT_NOT_FOUND)}

        val qna =qnaMapper.toEntity(project, member)
        qnaRepository.save(qna)
    }

    override fun updateAnswer(answerRequestDTO: AnswerRequestDTO, memberId: Long) {
        qnaRepository.updateAnswer(answerRequestDTO, memberId)
    }

    override fun deleteQuestion(questionId: Long, memberId: Long) {
        qnaRepository.deleteQuestion(questionId, memberId)
    }

    override fun updateQuestion(questionRequestDTO: UpdateQuestionRequestDTO, memberId: Long) {
        qnaRepository.updateQuestion(questionRequestDTO, memberId)
    }


}