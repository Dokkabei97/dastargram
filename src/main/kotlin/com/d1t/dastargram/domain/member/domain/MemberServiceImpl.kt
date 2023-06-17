package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberRequest.UpdateMemberRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(val memberStore: MemberStore, val memberReader: MemberReader) : MemberService {

    @Transactional
    override fun signUp(signUpMemberRequest: SignUpMemberRequest): Member {
        validateExistsEmail(signUpMemberRequest.email)
        validateExistsNickname(signUpMemberRequest.nickname)

        return memberStore.create(
                Member.create(
                        signUpMemberRequest.email,
                        signUpMemberRequest.password,
                        signUpMemberRequest.nickname,
                        signUpMemberRequest.name
                )
        )
    }

    @Transactional
    override fun update(updateMemberRequest: UpdateMemberRequest): Member {
        val member = memberReader.findById(updateMemberRequest.memberId)

        member.apply {
            updateMemberRequest.password?.let { password = it }
            updateMemberRequest.nickname?.let {
                validateExistsNickname(it)
                nickname = it
            }
            updateMemberRequest.name?.let { name = it }
            updateMemberRequest.profileImage?.let { profileImage = it }
        }

        return member
    }

    private fun validateExistsEmail(email: String) = require(!memberReader.isExistsByEmail(email)) { "이미 존재하는 이메일입니다." }

    private fun validateExistsNickname(nickname: String) = require(!memberReader.isExistsByNickname(nickname)) { "이미 존재하는 닉네임입니다." }

}