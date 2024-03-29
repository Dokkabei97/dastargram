package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class MemberTest : DescribeSpec({

    describe("Member.create") {
        context("이메일, 비밀번호, 닉네임이 주어졌을 때") {
            it("멤버를 생성한다.") {
                val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
                member.email shouldBe TEST_EMAIL
                member.password shouldBe TEST_PASSWORD
                member.nickname shouldBe TEST_NICKNAME
                member.name shouldBe TEST_NAME
                member.profileImage shouldBe null
                member.followerCount shouldBe 0
                member.followingCount shouldBe 0
            }
        }

        context("이메일이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create("", TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
                }
                exception.message shouldBe "이메일은 필수입니다."
            }
        }

        context("이메일이 30자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create("a".repeat(31), TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
                }
                exception.message shouldBe "이메일은 30자리 이하여야 합니다."
            }
        }

        context("이메일 형식이 올바르지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create("testemail.com", TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
                }
                exception.message shouldBe "이메일 형식이 올바르지 않습니다."
            }
        }

        context("비밀번호가 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, "", TEST_NICKNAME, TEST_NAME)
                }
                exception.message shouldBe "비밀번호는 필수입니다."
            }
        }

        context("닉네임이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, "", TEST_NAME)
                }
                exception.message shouldBe "닉네임은 필수입니다."
            }
        }

        context("닉네임이 2자리 미만일 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, "홍", TEST_NAME)
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임이 10자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, "a".repeat(11), TEST_NAME)
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임에 특수문자가 포함되었을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, "$TEST_NICKNAME!", TEST_NAME)
                }
                exception.message shouldBe "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다."
            }
        }

        context("이름이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, "")
                }
                exception.message shouldBe "이름은 필수입니다."
            }
        }

        context("이름이 2자리 미만일 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, "홍")
                }
                exception.message shouldBe "이름은 2~10자리여야 합니다."
            }
        }

        context("이름이 10자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, "a".repeat(11))
                }
                exception.message shouldBe "이름은 2~10자리여야 합니다."
            }
        }

        context("이름에 특수문자가 포함되었을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, "$TEST_NAME!")
                }
                exception.message shouldBe "이름은 한글, 영문으로만 구성되어야 합니다."
            }
        }
    }

    describe("Member.update") {
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)

        context("비밀번호를 변겅하면") {
            it("비밀번호가 변경된다.") {
                val newPassword = "test1234!"
                member.updatePassword(newPassword)
                member.password shouldBe newPassword
            }
        }

        context("비밀번호가 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updatePassword("")
                }
                exception.message shouldBe "비밀번호는 필수입니다."
            }
        }

        context("닉네임을 변경하면") {
            it("닉네임이 변경된다.") {
                val newNickname = "조선도적"
                member.updateNickname(newNickname)
                member.nickname shouldBe newNickname
            }
        }

        context("닉네임이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateNickname("")
                }
                exception.message shouldBe "닉네임은 필수입니다."
            }
        }

        context("닉네임이 2자리 미만일 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateNickname("홍")
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임이 10자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateNickname("a".repeat(11))
                }
                exception.message shouldBe "닉네임은 2~10자리여야 합니다."
            }
        }

        context("닉네임에 특수문자가 포함되었을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateNickname("$TEST_NICKNAME!")
                }
                exception.message shouldBe "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다."
            }
        }

        context("이름을 변경하면") {
            it("이름이 변경된다.") {
                val newName = "김길동"
                member.updateName(newName)
                member.name shouldBe newName
            }
        }

        context("이름이 주어지지 않았을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateName("")
                }
                exception.message shouldBe "이름은 필수입니다."
            }
        }

        context("이름이 2자리 미만일 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateName("홍")
                }
                exception.message shouldBe "이름은 2~10자리여야 합니다."
            }
        }

        context("이름이 10자리를 초과했을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateName("a".repeat(11))
                }
                exception.message shouldBe "이름은 2~10자리여야 합니다."
            }
        }

        context("이름에 특수문자가 포함되었을 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateName("$TEST_NAME!")
                }
                exception.message shouldBe "이름은 한글, 영문으로만 구성되어야 합니다."
            }
        }

        context("이름이 한글, 영문이 아닐 때") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.updateName("홍길동1")
                }
                exception.message shouldBe "이름은 한글, 영문으로만 구성되어야 합니다."
            }
        }

        context("팔로워 수 증가") {
            it("팔로워 수가 증가한다.") {
                member.increaseFollowerCount()
                member.followerCount shouldBe 1
            }
        }

        context("팔로워 수 감소") {
            it("팔로워 수가 감소한다.") {
                member.decreaseFollowerCount()
                member.followerCount shouldBe 0
            }
        }

        context("팔로워 수 감소할 때 팔로워 수가 0이면") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.decreaseFollowerCount()
                }
                exception.message shouldBe "팔로워 수는 0보다 작을 수 없습니다."
                member.followerCount shouldBe 0
            }
        }

        context("팔로잉 수 증가") {
            it("팔로잉 수가 증가한다.") {
                member.increaseFollowingCount()
                member.followingCount shouldBe 1
            }
        }

        context("팔로잉 수 감소") {
            it("팔로잉 수가 감소한다.") {
                member.decreaseFollowingCount()
                member.followingCount shouldBe 0
            }
        }

        context("팔로잉 수 감소할 때 팔로잉 수가 0이면") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    member.decreaseFollowingCount()
                }
                exception.message shouldBe "팔로잉 수는 0보다 작을 수 없습니다."
                member.followingCount shouldBe 0
            }
        }

        context("프로필 사진을 변경하면") {
            it("프로필 사진이 변경된다.") {
                val newProfileImage = "https://example.com/profile.jpg"
                member.updateProfileImage(newProfileImage)
                member.profileImage shouldBe newProfileImage
            }
        }

        context("프로필 사진을 삭제하면") {
            it("프로필 사진이 삭제된다.") {
                member.deleteProfileImage()
                member.profileImage shouldBe null
            }
        }
    }
})
