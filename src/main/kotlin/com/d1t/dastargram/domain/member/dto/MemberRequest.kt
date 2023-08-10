package com.d1t.dastargram.domain.member.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

sealed class MemberRequest(

        @field: Email(message = "이메일 형식이 올바르지 않습니다.")
        @field: NotBlank(message = "이메일은 필수입니다.")
        open val email: String,

        @field: NotBlank(message = "비밀번호는 필수입니다.")
        open val password: String,

        @field: NotBlank(message = "닉네임은 필수입니다.")
        open val nickname: String,

        @field: NotBlank(message = "이름은 필수입니다.")
        open val name: String,
) {

    data class SignUpMemberRequest(
            override val email: String,
            override val password: String,
            override val nickname: String,
            override val name: String,
    ) : MemberRequest(email, password, nickname, name)

    data class UpdateMemberRequest(
            val memberId: Long,
            val password: String?,
            val nickname: String?,
            val name: String?,
            val profileImage: String?,
    )
}