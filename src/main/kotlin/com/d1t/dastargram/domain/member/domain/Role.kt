package com.d1t.dastargram.domain.member.domain

enum class Role(key: String, title: String) {
    ADMIN("ROLE_ADMIN", "관리자"),
    MEMBER("ROLE_MEMBER", "회원")
}