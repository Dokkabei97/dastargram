package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.dto.MemberRequest
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.*

class MemberServiceImplTest : BehaviorSpec({

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
            every { memberReader.validateExistsByEmail(any()) } just Runs
            every { memberReader.validateExistsByNickname(any()) } just Runs
            every { memberStore.create(any()) } just Runs

            memberService.signUp(request)
            then("회원가입이 완료된다") {
                verify { memberReader.validateExistsByEmail(any()) }
                verify { memberReader.validateExistsByNickname(any()) }
                verify { memberStore.create(any()) }
            }
        }
    }
})
