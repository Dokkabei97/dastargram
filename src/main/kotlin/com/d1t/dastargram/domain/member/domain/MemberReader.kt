package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.search.dto.SearchMemberResponse

interface MemberReader {
    fun isExistsByEmail(email: String): Boolean
    fun isExistsByNickname(nickname: String): Boolean
    fun getMemberById(memberId: Long): Member
    fun getMemberByNickname(nickname: String): Member
    fun getMembersByName(name: String): List<Member>
    fun getMemberByEmail(email: String): Member
    fun getMemberByNicknameContainingOrNameContaining(keyword: String): List<Member>
}