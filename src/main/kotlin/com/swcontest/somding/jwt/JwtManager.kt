package com.swcontest.somding.jwt

import io.jsonwebtoken.Header
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import jakarta.servlet.http.HttpServletRequest
import org.apache.commons.lang3.StringUtils
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import com.swcontest.somding.service.auth.UserDetailServiceImpl
import java.nio.charset.StandardCharsets
import java.util.*

@Component
class JwtManager(
        @Value("\${jwt.secret}") private val JWT_SECRET: String,
        private val userDetailService: UserDetailServiceImpl
) {
    private val ACCESS_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 // 1h
    private val REFRESH_TOKEN_EXPIRE_TIME = 1000L * 60 * 60 * 24 * 7 // 7d

    fun validateTokenBoolean(token: String?): Boolean {
        val now = Date()
        return try {
            val claims = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray(StandardCharsets.UTF_8)))
                    .parseClaimsJws(token)
            !claims.body.expiration.before(now)
        } catch (e: JwtException) {
            false
        }
    }

    fun getMemberIdFromJwtToken(token: String?): Long {
        return try {
            val claims = Jwts.parser()
                    .setSigningKey(Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray(StandardCharsets.UTF_8)))
                    .parseClaimsJws(token)
                    .body
            claims.subject.toLong()
        } catch (e: Exception) {
            throw JwtException(e.message)
        }
    }

    fun generateAccessToken(userId: Long): String {
        val now = Date()
        return Jwts.builder()
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .setIssuer("allclear")
                .setIssuedAt(now)
                .setSubject(userId.toString())
                .setExpiration(Date(now.time + ACCESS_TOKEN_EXPIRE_TIME))
                .claim("memberId", userId)
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray(StandardCharsets.UTF_8)))
                .compact()
    }

    fun generateRefreshToken(memberId: Long): String {
        val now = Date()
        return Jwts.builder()
                .setIssuedAt(now)
                .setSubject(memberId.toString())
                .setExpiration(Date(now.time + REFRESH_TOKEN_EXPIRE_TIME))
                .claim("memberId", memberId)
                .claim("roles", "USER")
                .signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(JWT_SECRET.toByteArray(StandardCharsets.UTF_8)))
                .compact()
    }

    fun getAuthentication(token: String?): Authentication {
        println(getMemberIdFromJwtToken(token))
        val userDetails: UserDetails = userDetailService.loadUserByUsername(getMemberIdFromJwtToken(token).toString())
        return UsernamePasswordAuthenticationToken(userDetails, token, userDetails.authorities)
    }

    fun resolveToken(request: HttpServletRequest): String? {
        val bearerToken = request.getHeader(JwtAuthenticationFilter.AUTHORIZATION_HEADER)
        if (StringUtils.isNotEmpty(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7)
        }
        return null
    }
}
