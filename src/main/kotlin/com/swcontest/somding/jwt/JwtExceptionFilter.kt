package com.swcontest.somding.jwt

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.swcontest.somding.common.apiPayload.code.GeneralErrorCode
import com.swcontest.somding.common.apiPayload.exception.GeneralException
import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.RequiredArgsConstructor
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import java.time.LocalDateTime

@Component
@RequiredArgsConstructor
class JwtExceptionFilter : OncePerRequestFilter() {
    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            doFilter(request, response, filterChain)
        } catch (e: NullPointerException) {
            val body: MutableMap<String, Any> = HashMap()
            val mapper = ObjectMapper()
            mapper.registerModule(JavaTimeModule())
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            response.status = HttpServletResponse.SC_BAD_REQUEST // 예외에 맞는 HTTP 상태 코드 설정
            response.contentType = "application/json"
            body["timestamp"] = LocalDateTime.now()
            body["code"] = GeneralErrorCode.UNAUTHORIZED_401.code
            body["error"] = "Bad Request"
            body["message"] = GeneralErrorCode.UNAUTHORIZED_401.message // 예외에 맞는 메시지 설정
            body["path"] = request.requestURI
            mapper.writeValue(response.outputStream, body)
            logger.info("jwt exception nullpointer")
            throw GeneralException(GeneralErrorCode.UNAUTHORIZED_401)
        } catch (e: ExpiredJwtException) {
            val body: MutableMap<String, Any> = HashMap()
            val mapper = ObjectMapper()
            mapper.registerModule(JavaTimeModule())
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
            response.status = 419
            response.contentType = "application/json"
            body["timestamp"] = LocalDateTime.now()
            body["code"] = GeneralErrorCode.UNAUTHORIZED_401.code
            body["error"] = "Unauthorized"
            body["message"] = GeneralErrorCode.UNAUTHORIZED_401.message
            body["path"] = request.requestURI
            mapper.writeValue(response.outputStream, body)
        }
    }
}
