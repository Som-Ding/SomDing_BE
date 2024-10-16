package com.swcontest.somding.model.entity.member

import com.swcontest.somding.model.dto.request.member.UpdateProfileRequestDTO
import com.swcontest.somding.model.entity.common.BaseEntity
import com.swcontest.somding.model.entity.project.Project
import com.swcontest.somding.model.entity.qna.Qna
import com.swcontest.somding.model.entity.scrap.Scrap
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

@Entity
data class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        val memberId: Long,

        var email: String,
        private var password: String,  // private으로 변경
        var nickname: String,
        var profileImg: String?,

        var address: String?,
        var refreshToken: String?,

        @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
        var projectList: MutableList<Project>? = mutableListOf(),

        @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
        var scrapList: MutableList<Scrap>? = mutableListOf(),

        @OneToMany(mappedBy = "member", cascade = [CascadeType.ALL], orphanRemoval = true)
        var qnaList: MutableList<Qna>? = mutableListOf()
) : BaseEntity(), UserDetails {

        // refreshToken 재발급
        fun updateRefreshToken(refreshToken: String) {
                this.refreshToken = refreshToken
        }
        fun updateProfile(updateProfileRequestDTO: UpdateProfileRequestDTO){
                this.nickname = updateProfileRequestDTO.nickname
                this.profileImg = updateProfileRequestDTO.profileImg
        }

        // UserDetails 인터페이스 구현
        override fun getAuthorities(): Collection<GrantedAuthority>? {
                return null
        }

        override fun getUsername(): String {
                return this.email
        }

        override fun getPassword(): String {
                return this.password
        }

        override fun isAccountNonExpired(): Boolean {
                return true  // 활성 계정으로 설정
        }

        override fun isAccountNonLocked(): Boolean {
                return true  // 잠기지 않은 계정으로 설정
        }

        override fun isCredentialsNonExpired(): Boolean {
                return true  // 자격 증명이 만료되지 않도록 설정
        }

        override fun isEnabled(): Boolean {
                return true  // 활성 사용자로 설정
        }

        fun updatePassword(password: String) {
                this.password = password
        }

        fun refreshToken(refreshToken: String){
                this.refreshToken = refreshToken
        }
}
