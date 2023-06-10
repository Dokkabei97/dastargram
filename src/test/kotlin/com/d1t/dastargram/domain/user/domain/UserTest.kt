package com.d1t.dastargram.domain.user.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserTest : DescribeSpec({

    describe("User.create") {
        context("이메일, 비밀번호, 닉네임이 주어졌을 때") {
                val email = "test@email.com"
                val password = "testpassword"
                val nickname = "홍길동"
            it("유저를 생성한다.") {
                val user = User.create(email, password, nickname)
                user.email shouldBe email
                user.password shouldBe password
                user.nickname shouldBe nickname
            }
        }

        context("이메일이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    User.create("", "testpassword", "홍길동")
                }
                exception.message shouldBe "이메일은 필수입니다."
            }
        }

        context("이메일이 30자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("a".repeat(31), "testpassword", "홍길동")
                }
                exception.message shouldBe "이메일은 30자리 이하여야 합니다."
            }
        }

        context("이메일 형식이 올바르지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("testemail.com", "testpassword", "홍길동")
                }
                exception.message shouldBe "이메일 형식이 올바르지 않습니다."
            }
        }

        context("비밀번호가 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    User.create("test@email.com", "", "홍길동")
                }
                exception.message shouldBe "비밀번호는 필수입니다."
            }
        }

        context("비밀번호가 8자리 미만일 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("test@email.com", "test", "홍길동")
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        context("비밀번호가 15자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("test@email.com", "a".repeat(16), "홍길동")
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        context("닉네임이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    User.create("test@email.com", "testpassword", "")
                }
                exception.message shouldBe "닉네임은 필수입니다."
            }
        }

        context("닉네임이 2자리 미만일 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("test@email.com", "testpassword", "홍")
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임이 10자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("test@email.com", "testpassword", "a".repeat(11))
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임에 특수문자가 포함되었을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("test@email.com", "testpassword", "홍길동!")
                }
                exception.message shouldBe "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다."
            }
        }
    }
})
