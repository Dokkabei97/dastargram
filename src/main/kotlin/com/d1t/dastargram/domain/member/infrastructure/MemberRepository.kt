package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long> {
    fun existsByEmail(email: String): Boolean
    fun existsByNickname(nickname: String): Boolean
    fun findByName(name: String): List<Member>?
    fun findByNickname(nickname: String): Member?
    fun findByNameLike(name: String): List<Member>?
    fun findByNicknameLike(nickname: String): List<Member>?
}