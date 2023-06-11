package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest

interface MemberService {
    fun signUp(signUpMemberRequest: SignUpMemberRequest)
}