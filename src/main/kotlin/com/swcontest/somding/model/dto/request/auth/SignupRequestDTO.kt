package com.swcontest.somding.model.dto.request.auth

data class SignupRequestDTO(
        val email:String,
        val password: String,
        val nickname:String
)
