package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.dto.MemberRequest.SignUpMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberRequest.UpdateMemberRequest
import com.d1t.dastargram.domain.member.dto.MemberResponse.*
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class MemberServiceImpl(
        val memberStore: MemberStore,
        val memberReader: MemberReader,
        val passwordEncoder: PasswordEncoder
) : MemberService {

    @Transactional
    override fun signUp(signUpMemberRequest: SignUpMemberRequest): MemberPublicResponse {
        validateExistsEmail(signUpMemberRequest.email)
        validateExistsNickname(signUpMemberRequest.nickname)
        validatePassword(signUpMemberRequest.password)

        val member = memberStore.create(
                Member.create(
                        signUpMemberRequest.email,
                        passwordEncoder.encode(signUpMemberRequest.password),
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
                validatePassword(it)
                updatePassword(passwordEncoder.encode(it))
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

    override fun findById(memberId: Long): Member {
        return memberReader.getMemberById(memberId)
    }

    private fun validateExistsEmail(email: String) = require(!memberReader.isExistsByEmail(email)) { "이미 존재하는 이메일입니다." }

    private fun validateExistsNickname(nickname: String) = require(!memberReader.isExistsByNickname(nickname)) { "이미 존재하는 닉네임입니다." }

    private fun validatePassword(password: String) {
        require(password.length in 8..15) { "비밀번호는 8~15자리여야 합니다." }
        require(password.matches(Regex(PASSWORD_REGEX))) { "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#$%^&*()?_~)로만 구성되어야 합니다." }
    }

    companion object {
        private const val PASSWORD_REGEX = "^[a-zA-Z0-9!@#$%^&*()?_~]{8,15}$"
    }
}