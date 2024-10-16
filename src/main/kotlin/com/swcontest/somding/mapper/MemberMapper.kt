package com.swcontest.somding.mapper

import com.swcontest.somding.model.dto.request.auth.SignupRequestDTO
import com.swcontest.somding.model.entity.member.Member
import org.mapstruct.Mapper
import org.mapstruct.Mapping

@Mapper(componentModel = "spring")
interface MemberMapper {

    @Mapping(target = "memberId", ignore = true)
    @Mapping(target = "refreshToken", ignore = true)
    @Mapping(target = "password", source = "encodedPassword")
    fun toEntity(signupRequestDTO: SignupRequestDTO, encodedPassword: String): Member
}
