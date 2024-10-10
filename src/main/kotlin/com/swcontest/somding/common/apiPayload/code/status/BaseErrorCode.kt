package com.swcontest.somding.common.apiPayload.code.status

import com.swcontest.somding.common.apiPayload.ApiResponse
import org.springframework.http.HttpStatus

interface BaseErrorCode {
    val httpStatus: HttpStatus
    val code: String
    val message: String
    val errorResponse: ApiResponse<Void>
}
