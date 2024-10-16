package com.swcontest.somding.repository.member

import com.swcontest.somding.model.entity.member.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository : JpaRepository<Member, Long> {
    fun findByEmail(email: String): Member?
}