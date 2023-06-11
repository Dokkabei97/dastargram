package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberStore
import org.springframework.stereotype.Component

@Component
class MemberStoreImpl(val memberRepository: MemberRepository) : MemberStore {

    override fun create(member: Member) = memberRepository.save(member)
    override fun deleteById(memberId: Long) = memberRepository.deleteById(memberId)

}