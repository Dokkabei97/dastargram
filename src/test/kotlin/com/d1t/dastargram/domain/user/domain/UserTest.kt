package com.d1t.dastargram.domain.user.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class UserTest : DescribeSpec({

    describe("User.create") {
        context("이메일, 비밀번호, 닉네임이 주어졌을 때") {
            it("유저를 생성한다.") {
                val user = User.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME)
                user.email shouldBe TEST_EMAIL
                user.password shouldBe TEST_PASSWORD
                user.nickname shouldBe TEST_NICKNAME
            }
        }

        context("이메일이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    User.create("", TEST_PASSWORD, TEST_NICKNAME)
                }
                exception.message shouldBe "이메일은 필수입니다."
            }
        }

        context("이메일이 30자리를 초과했을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("a".repeat(31), TEST_PASSWORD, TEST_NICKNAME)
                }
                exception.message shouldBe "이메일은 30자리 이하여야 합니다."
            }
        }

        context("이메일 형식이 올바르지 않았을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create("testemail.com", TEST_PASSWORD, TEST_NICKNAME)
                }
                exception.message shouldBe "이메일 형식이 올바르지 않습니다."
            }
        }

        context("비밀번호가 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    User.create(TEST_EMAIL, "", TEST_NICKNAME)
                }
                exception.message shouldBe "비밀번호는 필수입니다."
            }
        }

        context("비밀번호가 8자리 미만일 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create(TEST_EMAIL, "test", TEST_NICKNAME)
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        context("비밀번호가 15자리를 초과했을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create(TEST_EMAIL, "a".repeat(16), TEST_NICKNAME)
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        context("비밀번호 사용 불가 특수문자가 있을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create(TEST_EMAIL, "$TEST_PASSWORD+", TEST_NICKNAME)
                }
                exception.message shouldBe "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#\$%^&*()?_~)로만 구성되어야 합니다."
            }
        }

        context("닉네임이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    User.create(TEST_EMAIL, TEST_PASSWORD, "")
                }
                exception.message shouldBe "닉네임은 필수입니다."
            }
        }

        context("닉네임이 2자리 미만일 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create(TEST_EMAIL, TEST_PASSWORD, "홍")
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임이 10자리를 초과했을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create(TEST_EMAIL, TEST_PASSWORD, "a".repeat(11))
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임에 특수문자가 포함되었을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    User.create(TEST_EMAIL, TEST_PASSWORD, "$TEST_NICKNAME!")
                }
                exception.message shouldBe "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다."
            }
        }
    }

    describe("User.update") {
        val user = User.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME)

        context("비밀번호를 변겅하면") {
            it("비밀번호가 변경된다.") {
                val newPassword = "test1234!"
                user.updatePassword(newPassword)
                user.password shouldBe newPassword
            }
        }

        context("닉네임을 변경하면") {
            it("닉네임이 변경된다.") {
                val newNickname = "김길동"
                user.updateNickname(newNickname)
                user.nickname shouldBe newNickname
            }
        }

        context("비밀번호가 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    user.updatePassword("")
                }
                exception.message shouldBe "비밀번호는 필수입니다."
            }
        }

        context("비밀번호가 8자리 미만일 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    user.updatePassword("test")
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        context("비밀번호가 15자리를 초과했을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    user.updatePassword("a".repeat(16))
                }
                exception.message shouldBe "비밀번호는 8~15자리여야 합니다."
            }
        }

        context("비밀번호 사용 불가 특수문자가 있을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    user.updatePassword("$TEST_PASSWORD+")
                }
                exception.message shouldBe "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#\$%^&*()?_~)로만 구성되어야 합니다."
            }
        }

        context("닉네임이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    user.updateNickname("")
                }
                exception.message shouldBe "닉네임은 필수입니다."
            }
        }

        context("닉네임이 2자리 미만일 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    user.updateNickname("홍")
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임이 10자리를 초과했을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    user.updateNickname("a".repeat(11))
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임에 특수문자가 포함되었을 때") {
            it("IllegalStateException을 던진다.") {
                val exception = shouldThrow<IllegalStateException> {
                    user.updateNickname("$TEST_NICKNAME!")
                }
                exception.message shouldBe "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다."
            }
        }
    }
}) {
    companion object {
        const val TEST_EMAIL = "test@example.com"
        const val TEST_PASSWORD = "test123T!"
        const val TEST_NICKNAME = "홍길동"
    }
}
