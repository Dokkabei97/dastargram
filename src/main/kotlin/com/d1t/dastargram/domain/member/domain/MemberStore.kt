package com.d1t.dastargram.domain.member.domain

interface MemberStore {
    fun create(member: Member)
    fun deleteById(memberId: Long)
}