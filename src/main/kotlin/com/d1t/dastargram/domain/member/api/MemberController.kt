package com.d1t.dastargram.domain.member.api

import com.d1t.dastargram.domain.member.application.MemberFacade
import com.d1t.dastargram.domain.member.dto.MemberRequest.*
import com.d1t.dastargram.domain.member.dto.MemberResponse
import com.d1t.dastargram.domain.member.dto.MemberResponse.*
import com.d1t.dastargram.global.common.response.CommonResponse
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
    fun signUp(@RequestBody @Validated signUpMemberRequest: SignUpMemberRequest): CommonResponse<MemberPublicResponse> {
        val member = memberFacade.signUp(signUpMemberRequest)
        return CommonResponse.success(member, "회원가입 성공")
    }

    @PutMapping()
    fun update(@RequestBody @Validated updateMemberRequest: UpdateMemberRequest): CommonResponse<MemberPublicResponse> {
        val member = memberFacade.update(updateMemberRequest)
        return CommonResponse.success(member, "회원정보 수정 성공")
    }

}