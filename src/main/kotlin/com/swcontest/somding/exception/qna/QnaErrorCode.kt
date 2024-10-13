package com.swcontest.somding.exception.qna

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
@AllArgsConstructor
enum class QnaErrorCode(override val httpStatus: HttpStatus, override val code: String, override val message: String) : BaseErrorCode {

   QUESTION_NOT_FOUND(HttpStatus.BAD_REQUEST, "QUESTION401", "해당 QNA를 찾을 수 없습니다"),

 PROJECT_DELETED_FAILED(HttpStatus.BAD_REQUEST, "PROJECT402", "프로젝트를 이미 후원한 사용자가 있어 삭제 불가능합니다.");

    // errorResponse 필드는 필요 없으므로 제거
    override val errorResponse: ApiResponse<Void>
        get() = ApiResponse.onFailure(code, message)
}
