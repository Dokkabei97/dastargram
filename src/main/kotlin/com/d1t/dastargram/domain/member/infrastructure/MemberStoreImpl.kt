package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.MemberStore
import org.springframework.stereotype.Component

@Component
class MemberStoreImpl(val memberRepository: MemberRepository): MemberStore {
}