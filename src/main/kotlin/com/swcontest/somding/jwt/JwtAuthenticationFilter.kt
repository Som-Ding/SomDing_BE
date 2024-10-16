package com.swcontest.somding.jwt

import io.jsonwebtoken.ExpiredJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.ServletException
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import lombok.extern.slf4j.Slf4j
import org.apache.commons.lang3.StringUtils
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter
import java.io.IOException

// Jwt 토큰으로 인증하는 필터입니다.
@Slf4j
class JwtAuthenticationFilter(authenticationManager: AuthenticationManager?, jwtManager: JwtManager) : BasicAuthenticationFilter(authenticationManager) {
    private val jwtManager: JwtManager

    init {
        this.jwtManager = jwtManager
    }

    @Throws(IOException::class, ServletException::class)
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, chain: FilterChain) {
        logger.info("여기")
        //헤더에서 토큰 가져오기
        val token: String? = jwtManager.resolveToken(request)
        val requestURI = request.requestURI

        // 토큰이 존재 여부 + 토큰 검증
        if (StringUtils.isNotEmpty(token) && jwtManager.validateTokenBoolean(token)) {
            logger.info("토큰 검증")
            val authentication: Authentication = jwtManager.getAuthentication(token)

            // security 세션에 등록
            SecurityContextHolder.getContext().authentication = authentication
            logger.info("Security Context에 '{}' 인증 정보를 저장했습니다, uri: {}")
        } else if (StringUtils.isEmpty(token)) { // 널일때
            throw NullPointerException()
        } else if (!jwtManager.validateTokenBoolean(token)) {
            logger.info("유효한 JWT 토큰이 없습니다, uri: {} $requestURI")
            throw ExpiredJwtException(null, null, "유효하지 않은 Access Token입니다.")
        }
        chain.doFilter(request, response)
    }

    companion object {
        const val AUTHORIZATION_HEADER = "Authorization"
    }
}
