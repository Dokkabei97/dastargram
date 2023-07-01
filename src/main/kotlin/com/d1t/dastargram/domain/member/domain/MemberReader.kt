package com.d1t.dastargram.domain.member.domain

interface MemberReader {
    fun isExistsByEmail(email: String): Boolean
    fun isExistsByNickname(nickname: String): Boolean
    fun getMemberById(memberId: Long): Member
    fun getMemberByNickname(nickname: String): Member
    fun getMembersByName(name: String): List<Member>
}