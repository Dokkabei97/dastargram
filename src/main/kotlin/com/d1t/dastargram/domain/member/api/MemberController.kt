package com.d1t.dastargram.domain.member.api

import com.d1t.dastargram.domain.member.application.MemberFacade
import com.d1t.dastargram.domain.member.dto.MemberRequest.*
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/members")
class MemberController(val memberFacade: MemberFacade) {

    @PostMapping
    fun signUp(@RequestBody @Validated signUpMemberRequest: SignUpMemberRequest): ResponseEntity<String> {
        memberFacade.signUp(signUpMemberRequest)
        return ResponseEntity.ok("회원가입 성공")
    }

    @PutMapping("/{id}")
    fun update(@RequestBody @Validated updateMemberRequest: UpdateMemberRequest, @PathVariable id: Long): ResponseEntity<String> {
        memberFacade.update(updateMemberRequest)
        return ResponseEntity.ok("회원정보 수정 성공")
    }

}