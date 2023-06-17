package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberRequest.UpdateMemberRequest

interface MemberService {
    fun signUp(signUpMemberRequest: SignUpMemberRequest): Member
    fun update(updateMemberRequest: UpdateMemberRequest): Member
}