package com.swcontest.somding.common.apiPayload

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import org.springframework.http.HttpStatus

@JsonPropertyOrder("code", "message", "result")
data class ApiResponse<T>(
        val code: String,
        val message: String,
        @JsonInclude(JsonInclude.Include.NON_NULL)
        val result: T? = null
) {
    companion object {
        fun <T> onSuccess(result: T): ApiResponse<T> {
            return ApiResponse(HttpStatus.OK.value().toString(), HttpStatus.OK.reasonPhrase, result)
        }

        fun <T> onSuccess(status: HttpStatus, result: T): ApiResponse<T> {
            return ApiResponse(status.value().toString(), status.reasonPhrase, result)
        }

        fun <T> onFailure(code: String, message: String, result: T? = null): ApiResponse<T> {
            return ApiResponse(code, message, result)
        }
    }
}
