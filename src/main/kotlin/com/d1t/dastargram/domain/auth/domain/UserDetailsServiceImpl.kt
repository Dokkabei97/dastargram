package com.d1t.dastargram.domain.auth.domain

import com.d1t.dastargram.domain.member.domain.MemberReader
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Component

@Component
class UserDetailsServiceImpl(val memberReader: MemberReader): UserDetailsService {

    override fun loadUserByUsername(email: String): UserDetails {
        val member = memberReader.getMemberByEmail(email)
        return UserDetailsImpl(member)
    }
}