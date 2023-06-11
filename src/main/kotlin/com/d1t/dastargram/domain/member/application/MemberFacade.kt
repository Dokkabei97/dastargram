package com.d1t.dastargram.domain.member.application

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberService
import com.d1t.dastargram.domain.member.dto.MemberRequest
import com.d1t.dastargram.domain.member.dto.MemberRequest.*
import org.springframework.stereotype.Service

@Service
class MemberFacade(val memberService: MemberService) {

    fun signUp(signUpMemberRequest: SignUpMemberRequest) = memberService.signUp(signUpMemberRequest)
    fun update(updateMemberRequest: UpdateMemberRequest) = memberService.update(updateMemberRequest)

}