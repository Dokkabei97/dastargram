package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(val memberStore: MemberStore, val memberReader: MemberReader) : MemberService {

    @Transactional
    override fun signUp(signUpMemberRequest: SignUpMemberRequest) {
        validateExists(signUpMemberRequest.email, signUpMemberRequest.nickname)

        val member = Member.create(
                signUpMemberRequest.email,
                signUpMemberRequest.password,
                signUpMemberRequest.nickname,
                signUpMemberRequest.name
        )

        memberStore.create(member)
    }

    private fun validateExists(email: String, nickname: String) {
        require(!memberReader.isExistsByEmail(email)) { "이미 존재하는 이메일입니다." }
        require(!memberReader.isExistsByNickname(nickname)) { "이미 존재하는 닉네임입니다." }
    }
}