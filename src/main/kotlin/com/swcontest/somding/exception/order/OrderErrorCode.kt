package com.swcontest.somding.exception.order

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import lombok.AllArgsConstructor
import lombok.Getter
import org.springframework.http.HttpStatus

@Getter
@AllArgsConstructor
enum class OrderErrorCode(override val httpStatus: HttpStatus, override val code: String, override val message: String) : BaseErrorCode {
    // 해당 멤버가 없을 때
    ORDER_NOT_FOUND(HttpStatus.NOT_FOUND, "ORDER401", "해당 주문를 찾을 수 없습니다");

    override val errorResponse: ApiResponse<Void>
        get() = ApiResponse.onFailure(code, message)
}
