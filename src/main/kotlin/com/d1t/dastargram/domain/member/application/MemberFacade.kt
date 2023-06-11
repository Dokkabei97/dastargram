package com.d1t.dastargram.domain.member.application

import com.d1t.dastargram.domain.member.domain.MemberService
import org.springframework.stereotype.Service

@Service
class MemberFacade(val memberService: MemberService) {
}