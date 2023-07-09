package com.d1t.dastargram.domain.auth.domain.security

import com.d1t.dastargram.domain.member.domain.Member
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserDetailsImpl(val member: Member) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()
        authorities.add(GrantedAuthority { member.role.name })
        return authorities
    }

    override fun getPassword(): String = member.password

    override fun getUsername(): String = member.email

    // 계정 만료 여부
    override fun isAccountNonExpired(): Boolean = true

    // 계정 잠김 여부
    override fun isAccountNonLocked(): Boolean = true

    // 비밀번호 만료 여부
    override fun isCredentialsNonExpired(): Boolean = true

    // 계정 활성화 여부
    override fun isEnabled(): Boolean = true
}