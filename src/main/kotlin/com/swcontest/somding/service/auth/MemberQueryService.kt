package com.swcontest.somding.service.auth

import com.swcontest.somding.model.dto.response.member.ReadProfileResponseDTO
import com.swcontest.somding.model.entity.member.Member

interface MemberQueryService {
    fun readProfile(member: Member) : ReadProfileResponseDTO

}