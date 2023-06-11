package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.MemberReader
import org.springframework.stereotype.Component

@Component
class MemberReaderImpl(val memberRepository: MemberRepository): MemberReader {
}