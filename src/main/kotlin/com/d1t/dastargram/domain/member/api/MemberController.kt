package com.d1t.dastargram.domain.member.api

import com.d1t.dastargram.domain.member.application.MemberFacade
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class MemberController(val memberFacade: MemberFacade) {

    @GetMapping
    fun healthCheck(): String {
        return "OK"
    }
}