package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberRequest.UpdateMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberResponse.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(val memberStore: MemberStore, val memberReader: MemberReader) : MemberService {

    @Transactional
    override fun signUp(signUpMemberRequest: SignUpMemberRequest): MemberPublicResponse {
        validateExistsEmail(signUpMemberRequest.email)
        validateExistsNickname(signUpMemberRequest.nickname)

        val member = memberStore.create(
                Member.create(
                        signUpMemberRequest.email,
//                        passwordEncoder.encode(signUpMemberRequest.password),
                        signUpMemberRequest.password,
                        signUpMemberRequest.nickname,
                        signUpMemberRequest.name
                )
        )

        return MemberPublicResponse(
                member.nickname,
                member.name,
                member.profileImage,
                member.followerCount,
                member.followingCount
        )
    }

    @Transactional
    override fun update(updateMemberRequest: UpdateMemberRequest): MemberPublicResponse {
        val member = memberReader.getMemberById(updateMemberRequest.memberId)

        member.apply {
            updateMemberRequest.password?.let {
//                updatePassword(passwordEncoder.encode(it))
                updatePassword(it)
            }
            updateMemberRequest.nickname?.let {
                validateExistsNickname(it)
                updateNickname(it)
            }
            updateMemberRequest.name?.let { updateName(it) }
            updateMemberRequest.profileImage?.let { updateProfileImage(it) }
        }

        return MemberPublicResponse(
                member.nickname,
                member.name,
                member.profileImage,
                member.followerCount,
                member.followingCount
        )
    }

    private fun validateExistsEmail(email: String) = require(!memberReader.isExistsByEmail(email)) { "이미 존재하는 이메일입니다." }

    private fun validateExistsNickname(nickname: String) = require(!memberReader.isExistsByNickname(nickname)) { "이미 존재하는 닉네임입니다." }

}