package com.swcontest.somding.common.apiPayload.code

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import org.springframework.http.HttpStatus

enum class GeneralErrorCode(
        override val httpStatus: HttpStatus,
        override val code: String,
        override val message: String
) : BaseErrorCode {
    BAD_REQUEST_400(HttpStatus.BAD_REQUEST, "COMMON400", "잘못된 요청입니다"),
    UNAUTHORIZED_401(HttpStatus.UNAUTHORIZED, "COMMON401", "인증이 필요합니다"),
    FORBIDDEN_403(HttpStatus.FORBIDDEN, "COMMON403", "접근이 금지되었습니다"),
    NOT_FOUND_404(HttpStatus.NOT_FOUND, "COMMON404", "요청한 자원을 찾을 수 없습니다"),
    INTERNAL_SERVER_ERROR_500(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 내부 오류가 발생했습니다"),
    VALIDATION_FAILED(HttpStatus.BAD_REQUEST, "VALID400", "입력값에 대한 검증에 실패했습니다");

    override val errorResponse: ApiResponse<Void>
        get() = ApiResponse.onFailure(code, message)
}
