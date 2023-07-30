package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.search.dto.SearchMemberResponse
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface MemberRepository: JpaRepository<Member, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
    fun findByName(name: String): List<Member>?
    fun findByNickname(nickname: String): Member?
    fun findByNameLike(name: String): List<Member>?
    fun findByNicknameLike(nickname: String): List<Member>?
    fun findByEmail(email: String): Member?
    fun findByNicknameContainingIgnoreCaseOrNameContainingIgnoreCase(nickname: String, name: String): List<Member>
}