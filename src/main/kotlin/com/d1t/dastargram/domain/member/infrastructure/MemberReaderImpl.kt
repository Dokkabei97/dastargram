package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.MemberReader
import org.springframework.stereotype.Component

@Component
class MemberReaderImpl(val memberRepository: MemberRepository) : MemberReader {

    override fun validateExistsByEmail(email: String) {
        require(!memberRepository.existsByEmail(email)) { "이미 존재하는 이메일입니다." }
    }

    override fun validateExistsByNickname(nickname: String) {
        require(!memberRepository.existsByNickname(nickname)) { "이미 존재하는 닉네임입니다." }
    }
}