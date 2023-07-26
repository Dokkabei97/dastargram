package com.d1t.dastargram.domain.member.infrastructure

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class MemberReaderImpl(val memberRepository: MemberRepository) : MemberReader {

    override fun isExistsByEmail(email: String): Boolean = memberRepository.existsByEmail(email)
    override fun isExistsByNickname(nickname: String): Boolean = memberRepository.existsByNickname(nickname)
    override fun getMemberById(memberId: Long): Member = memberRepository.findByIdOrNull(memberId)
            ?: throw EntityNotFoundException("존재하지 않는 회원입니다.")

    override fun getMemberByNickname(nickname: String): Member = memberRepository.findByNickname(nickname)
            ?: throw EntityNotFoundException("존재하지 않는 회원입니다.")

    override fun getMembersByName(name: String): List<Member> = memberRepository.findByName(name)
            ?: throw EntityNotFoundException("존재하지 않는 회원입니다.")

    override fun getMemberByEmail(email: String): Member = memberRepository.findByEmail(email)
            ?: throw EntityNotFoundException("존재하지 않는 회원입니다.")

    override fun getMemberByNicknameContaining(keyword: String): List<SearchMemberResponse> = memberRepository.findByNicknameContainingIgnoreCase(keyword)


}