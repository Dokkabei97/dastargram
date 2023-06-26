package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.dto.MemberRequest
import com.d1t.dastargram.domain.member.dto.MemberResponse
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class MemberServiceImplTest : BehaviorSpec({
    val memberStore: MemberStore = mockk()
    val memberReader: MemberReader = mockk()
    val memberService = MemberServiceImpl(memberStore, memberReader)

    given("회원가입 요청") {
        val memberRequest = MemberRequest.SignUpMemberRequest(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        val memberResponse = MemberResponse.MemberPublicResponse(TEST_NICKNAME, TEST_NAME, null)

        every { memberReader.isExistsByEmail(any()) } returns false
        every { memberReader.isExistsByNickname(any()) } returns false
        every { memberStore.create(any()) } returns member

        `when`("회원가입 요청") {
            val result = memberService.signUp(memberRequest)

            then("회원가입 성공") {
                result shouldBe memberResponse

                verify(exactly = 1) { memberStore.create(any()) }
            }
        }
    }

    given("회원정보 수정 요청") {
        val memberId = 1L
        val memberRequest = MemberRequest.UpdateMemberRequest(memberId, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME, null)
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        val memberResponse = MemberResponse.MemberPublicResponse(TEST_NICKNAME, TEST_NAME, null)

        every { memberReader.findById(any()) } returns member
        every { memberReader.isExistsByNickname(any()) } returns false

        `when`("회원정보 수정 요청") {
            val result = memberService.update(memberRequest)

            then("회원정보 수정 성공") {
                result shouldBe memberResponse

                verify(exactly = 1) { memberReader.findById(any()) }
            }
        }
    }
})