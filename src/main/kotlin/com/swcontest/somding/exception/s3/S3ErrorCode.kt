package com.swcontest.somding.exception.s3

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
@AllArgsConstructor
enum class S3ErrorCode(override val httpStatus: HttpStatus, override val code: String, override val message: String) : BaseErrorCode {

    UPLOAD_FAILED(HttpStatus.BAD_REQUEST, "IMAGE401", "image를 업로드할 수 없습니다"),

    IMAGE_NOT_FOUND(HttpStatus.BAD_REQUEST, "IMAGE402", "image를 찾을 수 없습니다"),
    SIZE_MISMATCH(HttpStatus.BAD_REQUEST, "IMAGE402", "image를 찾을 수 없습니다")
    ;

    // errorResponse 필드는 필요 없으므로 제거
    override val errorResponse: ApiResponse<Void>
        get() = ApiResponse.onFailure(code, message)
}
