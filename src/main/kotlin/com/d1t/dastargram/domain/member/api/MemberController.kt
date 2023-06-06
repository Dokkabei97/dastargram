package com.d1t.dastargram.domain.member.api

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("/member")
class MemberController {
    @GetMapping("/")
    fun getMember(): String {
        return "Hello world";
    }
}
