package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.dto.MemberRequest
import com.d1t.dastargram.domain.member.dto.MemberRequest.*
import com.d1t.dastargram.domain.member.dto.MemberResponse
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import jakarta.persistence.EntityNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder

class MemberServiceImplTest : BehaviorSpec({
    val memberStore: MemberStore = mockk()
    val memberReader: MemberReader = mockk()
    val passwordEncoder: PasswordEncoder = mockk()
    val memberService = MemberServiceImpl(memberStore, memberReader, passwordEncoder)

    given("회원가입 요청") {
        val memberRequest = SignUpMemberRequest(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        val memberResponse = MemberResponse.MemberPublicResponse(TEST_NICKNAME, TEST_NAME, null)

        every { memberReader.isExistsByEmail(any()) } returns false
        every { memberReader.isExistsByNickname(any()) } returns false
        every { passwordEncoder.encode(any()) } returns TEST_PASSWORD
        every { memberStore.create(any()) } returns member

        `when`("회원가입 요청") {
            val result = memberService.signUp(memberRequest)

            then("회원가입 성공") {
                result shouldBe memberResponse

                verify(exactly = 1) { memberStore.create(any()) }
            }
        }

        `when`("비밀번호가 8자리 미만으로 회원가입 요청") {
            val passwordLengthUnder = SignUpMemberRequest(TEST_EMAIL, "test", TEST_NICKNAME, TEST_NAME)

            then("회원가입 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.signUp(passwordLengthUnder)
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        `when`("비밀번호가 15자리 초과로 회원가입 요청") {
            val passwordLengthOver = SignUpMemberRequest(TEST_EMAIL, "a".repeat(16), TEST_NICKNAME, TEST_NAME)

            then("회원가입 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.signUp(passwordLengthOver)
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        `when`("비밀번호 사용 불가 특수문자가 있을 때") {
            val passwordSpecialCharacter = SignUpMemberRequest(TEST_EMAIL, "$TEST_PASSWORD+", TEST_NICKNAME, TEST_NAME)

            then("회원가입 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.signUp(passwordSpecialCharacter)
                }
                exception.message shouldBe "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#\$%^&*()?_~)로만 구성되어야 합니다."
            }
        }


        `when`("이미 존재하는 이메일로 회원가입 요청") {
            every { memberReader.isExistsByEmail(any()) } returns true
            every { memberReader.isExistsByNickname(any()) } returns false

            then("회원가입 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.signUp(memberRequest)
                }
                exception.message shouldBe "이미 존재하는 이메일입니다."
            }
        }

        `when`("이미 존재하는 닉네임으로 회원가입 요청") {
            every { memberReader.isExistsByEmail(any()) } returns false
            every { memberReader.isExistsByNickname(any()) } returns true

            then("회원가입 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.signUp(memberRequest)
                }
                exception.message shouldBe "이미 존재하는 닉네임입니다."
            }
        }
    }

    given("회원정보 수정 요청") {
        val memberId = 1L
        val memberRequest = UpdateMemberRequest(memberId, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME, null)
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        val memberResponse = MemberResponse.MemberPublicResponse(TEST_NICKNAME, TEST_NAME, null)

        every { memberReader.getMemberById(any()) } returns member
        every { memberReader.isExistsByNickname(any()) } returns false

        `when`("회원정보 수정 요청") {
            val result = memberService.update(memberRequest)

            then("회원정보 수정 성공") {
                result shouldBe memberResponse

                verify(exactly = 1) { memberReader.getMemberById(any()) }
            }
        }

        `when`("비밀번호가 8자리 미만으로 회원정보 수정 요청") {
            val passwordLengthUnder = UpdateMemberRequest(memberId, "test", TEST_NICKNAME, TEST_NAME, null)

            then("회원정보 수정 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.update(passwordLengthUnder)
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        `when`("비밀번호가 15자리 초과로 회원정보 수정 요청") {
            val passwordLengthOver = UpdateMemberRequest(memberId, "a".repeat(16), TEST_NICKNAME, TEST_NAME, null)

            then("회원정보 수정 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.update(passwordLengthOver)
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        `when`("비밀번호 사용 불가 특수문자가 있을 때") {
            val passwordSpecialCharacter = UpdateMemberRequest(memberId, "$TEST_PASSWORD+", TEST_NICKNAME, TEST_NAME, null)

            then("회원정보 수정 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.update(passwordSpecialCharacter)
                }
                exception.message shouldBe "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#\$%^&*()?_~)로만 구성되어야 합니다."
            }
        }

        `when`("이미 존재하는 닉네임으로 회원정보 수정 요청") {
            every { memberReader.isExistsByNickname(any()) } returns true

            then("회원정보 수정 실패") {
                val exception = shouldThrow<IllegalArgumentException> {
                    memberService.update(memberRequest)
                }
                exception.message shouldBe "이미 존재하는 닉네임입니다."
            }
        }

        `when`("존재하지 않는 회원으로 회원정보 수정 요청") {
            every { memberReader.getMemberById(any()) } throws EntityNotFoundException("존재하지 않는 회원입니다.")

            then("회원정보 수정 실패") {
                val exception = shouldThrow<EntityNotFoundException> {
                    memberService.update(memberRequest)
                }
                exception.message shouldBe "존재하지 않는 회원입니다."
            }
        }
    }
})