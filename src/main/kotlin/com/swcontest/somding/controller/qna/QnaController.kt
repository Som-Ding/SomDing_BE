package com.swcontest.somding.controller.qna

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.model.dto.request.AnswerRequestDTO
import com.swcontest.somding.model.dto.request.CreateQuestionRequestDTO
import com.swcontest.somding.model.dto.request.UpdateQuestionRequestDTO
import com.swcontest.somding.model.dto.response.QnaDetailResponseDTO
import com.swcontest.somding.model.dto.response.QnaResponseDTO
import com.swcontest.somding.model.entity.member.Member
import com.swcontest.somding.service.qna.QnaCommandService
import com.swcontest.somding.service.qna.QnaQueryService
import io.swagger.v3.oas.annotations.Operation
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class QnaController(private val qnaQueryService: QnaQueryService,
        private val qnaCommandService: QnaCommandService) {

    @Operation(summary = "질문 생성")
    @PostMapping("/questions")
    fun creatQna(@RequestBody qnaRequestDTO: CreateQuestionRequestDTO): ApiResponse<String?>{
        qnaCommandService.createQuestion(qnaRequestDTO)
        return ApiResponse.onSuccess(null)
    }

    //조회
    @Operation(summary = "질문 전체조회")
    @GetMapping("projects/questions/{projectId}")
    fun readQna(@PathVariable("projectId")projectId:Long):ApiResponse<List<QnaResponseDTO>>{
        return ApiResponse.onSuccess(qnaQueryService.readQna(projectId))
    }

    //답 등록
    @Operation(summary = "질문 답 등록")
    @PatchMapping("/questions/answer")
    fun updateAnswer(@RequestBody answerRequestDTO:AnswerRequestDTO, @AuthenticationPrincipal member: Member):ApiResponse<String?>{
        qnaCommandService.updateAnswer(answerRequestDTO, member)
        return ApiResponse.onSuccess(null)
    }

    //질문 수정
    @Operation(summary = "질문 수정")
    @PatchMapping("/questions")
    fun updateQuestion(@RequestBody questionRequestDTO: UpdateQuestionRequestDTO, @AuthenticationPrincipal member: Member): ApiResponse<String?>{
        qnaCommandService.updateQuestion(questionRequestDTO, member)
        return ApiResponse.onSuccess(null)
    }


    //질문 삭제
    @Operation(summary = "질문 삭제")
    @DeleteMapping("questions/{questionId}")
    fun deleteQuestion(@PathVariable("questionId") questionId:Long, @AuthenticationPrincipal member: Member): ApiResponse<String?>{
        qnaCommandService.deleteQuestion(questionId, member)
        return ApiResponse.onSuccess(null)
    }
    //질문 디테일 조회
    @Operation(summary = "질문 디테일 조회")
    @GetMapping("questions/{questionId}")
    fun readQnaDetail(@PathVariable("questionId") questionId:Long) :ApiResponse<QnaDetailResponseDTO>{
        return ApiResponse.onSuccess(qnaQueryService.readQnaDetail(questionId))
    }

}