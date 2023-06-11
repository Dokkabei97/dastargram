package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(val memberStore: MemberStore, val memberReader: MemberReader): MemberService {

    @Transactional
    override fun signUp(signUpMemberRequest: SignUpMemberRequest) {
        memberReader.validateExistsByEmail(signUpMemberRequest.email)
        memberReader.validateExistsByNickname(signUpMemberRequest.nickname)

        val member = Member.create(
                signUpMemberRequest.email,
                signUpMemberRequest.password,
                signUpMemberRequest.nickname,
                signUpMemberRequest.name
        )

        memberStore.create(member)
    }
}