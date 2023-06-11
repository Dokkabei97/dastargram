package com.d1t.dastargram.domain.member.domain

interface MemberReader {
    fun validateExistsByEmail(email: String)
    fun validateExistsByNickname(nickname: String)
}