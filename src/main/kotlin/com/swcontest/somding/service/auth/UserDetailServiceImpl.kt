package com.swcontest.somding.service.auth

import com.swcontest.somding.exception.member.MemberErrorCode
import com.swcontest.somding.exception.member.MemberException
import com.swcontest.somding.repository.member.MemberRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailServiceImpl(
        private val memberRepository: MemberRepository
) : UserDetailsService {

    @Throws(UsernameNotFoundException::class)
    override fun loadUserByUsername(memberId: String): UserDetails {
        println("로그인한 memberId : $memberId")
        val result = memberRepository.findById(memberId.toLong())
                .orElseThrow { MemberException(MemberErrorCode.MEMBER_NOT_FOUND) } as UserDetails

        return result
    }
}
