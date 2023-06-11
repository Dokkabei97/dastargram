package com.d1t.dastargram.domain.member.domain

interface MemberReader {
    fun isExistsByEmail(email: String): Boolean
    fun isExistsByNickname(nickname: String): Boolean
}