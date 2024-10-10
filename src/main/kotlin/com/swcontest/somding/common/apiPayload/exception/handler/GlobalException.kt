package com.swcontest.somding.common.apiPayload.exception.handler

import com.swcontest.somding.common.apiPayload.ApiResponse
import com.swcontest.somding.common.apiPayload.code.GeneralErrorCode
import com.swcontest.somding.common.apiPayload.code.status.BaseErrorCode
import com.swcontest.somding.common.apiPayload.exception.GeneralException
import jakarta.validation.ConstraintViolation
import jakarta.validation.ConstraintViolationException
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    private val log = LoggerFactory.getLogger(GlobalExceptionHandler::class.java)

    @ExceptionHandler(ConstraintViolationException::class)
    fun handleConstraintViolationException(e: ConstraintViolationException): ResponseEntity<ApiResponse<String>> {
        val errorMessage = e.constraintViolations
                .map { it.message }
                .firstOrNull() ?: "ConstraintViolationException 추출 도중 에러 발생"
        return ResponseEntity
                .status(GeneralErrorCode.VALIDATION_FAILED.httpStatus)
                .body(ApiResponse.onFailure(GeneralErrorCode.VALIDATION_FAILED.code, errorMessage))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleMethodArgumentNotValidException(
            ex: MethodArgumentNotValidException
    ): ResponseEntity<ApiResponse<Map<String, String>>> {
        val failedValidations = ex.bindingResult.fieldErrors.associate { it.field to (it.defaultMessage ?: "Invalid value") }
        val errorResponse = ApiResponse.onFailure(
                GeneralErrorCode.VALIDATION_FAILED.code,
                GeneralErrorCode.VALIDATION_FAILED.message,
                failedValidations
        )
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorResponse)
    }


    @ExceptionHandler(GeneralException::class)
    fun handleCustomException(e: GeneralException): ResponseEntity<ApiResponse<Void>> {
        log.warn("[WARNING] Custom Exception : {}", e.errorCode)
        val errorCode: BaseErrorCode = e.errorCode
        return ResponseEntity.status(errorCode.httpStatus).body(errorCode.errorResponse)
    }

    @ExceptionHandler(Exception::class)
    fun handleAllException(e: Exception): ResponseEntity<ApiResponse<String>> {
        log.error("[WARNING] Internal Server Error : {} ", e.message)
        val errorCode: BaseErrorCode = GeneralErrorCode.INTERNAL_SERVER_ERROR_500
        val errorResponse = ApiResponse.onFailure(
                errorCode.code,
                errorCode.message,
                e.message
        )
        return ResponseEntity
                .status(errorCode.httpStatus)
                .body(errorResponse)
    }
}
