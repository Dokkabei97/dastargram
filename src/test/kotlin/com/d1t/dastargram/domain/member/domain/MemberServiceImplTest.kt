package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.dto.MemberRequest
import com.d1t.dastargram.domain.member.dto.MemberResponse
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*

class MemberServiceImplTest : BehaviorSpec({
    val member = mockk<Member>()
    val memberResponse = mockk<MemberResponse.MemberPublicResponse>()
    val memberReader = mockk<MemberReader>()
    val memberStore = mockk<MemberStore>()

    val memberService = MemberServiceImpl(memberStore, memberReader)

    given("회원가입 요청이 들어온다") {
        val request = MemberRequest.SignUpMemberRequest(
                TEST_EMAIL,
                TEST_PASSWORD,
                TEST_NICKNAME,
                TEST_NAME
        )
        `when`("회원가입을 한다") {
            every { memberReader.isExistsByEmail(any()) } returns false
            every { memberReader.isExistsByNickname(any()) } returns false
            every { memberStore.create(any()) } returns member

            memberService.signUp(request)
            then("회원가입이 완료된다") {
                verify { memberReader.isExistsByEmail(any()) }
                verify { memberReader.isExistsByNickname(any()) }
                verify { memberStore.create(any()) }
            }
        }
    }

    given("업데이트 요청이 들어온다") {
        val request = MemberRequest.UpdateMemberRequest(
                1L,
                TEST_PASSWORD,
                TEST_NICKNAME,
                TEST_NAME,
                "profileImage"
        )
        `when`("업데이트를 한다") {
            every { memberReader.findById(any()) } returns Member.create(
                    TEST_EMAIL,
                    TEST_PASSWORD,
                    TEST_NICKNAME,
                    TEST_NAME
            )
            every { memberReader.isExistsByNickname(any()) } returns false

            memberService.update(request)
            then("업데이트가 완료된다") {
                verify { memberReader.findById(any()) }
                verify { memberReader.isExistsByNickname(any()) }
            }
        }
    }
})
