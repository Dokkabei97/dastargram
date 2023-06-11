package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Component

@Component
class MemberReaderImpl(val memberRepository: MemberRepository) : MemberReader {

    override fun isExistsByEmail(email: String): Boolean = memberRepository.existsByEmail(email)
    override fun isExistsByNickname(nickname: String): Boolean = memberRepository.existsByNickname(nickname)
    override fun findById(memberId: Long): Member = memberRepository.findById(memberId)
            .orElseThrow { EntityNotFoundException("존재하지 않는 회원입니다.") }
}