package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberRequest.UpdateMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberResponse.*

interface MemberService {
    fun signUp(signUpMemberRequest: SignUpMemberRequest): MemberPublicResponse
    fun update(updateMemberRequest: UpdateMemberRequest): MemberPublicResponse
}