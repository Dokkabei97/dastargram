package com.d1t.dastargram.domain.member.application

import com.d1t.dastargram.domain.member.domain.MemberService
import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import org.springframework.stereotype.Service

@Service
class MemberFacade(val memberService: MemberService) {

    fun signUp(signUpMemberRequest: SignUpMemberRequest) {
        memberService.signUp(signUpMemberRequest)
    }
}