package com.swcontest.somding.model.dto.response.auth

data class TokenResponseDTO(
        val accessToken: String,
        val refreshToken: String
)
