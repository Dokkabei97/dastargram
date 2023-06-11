package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.Member
import org.springframework.data.jpa.repository.JpaRepository

interface MemberRepository: JpaRepository<Member, Long>