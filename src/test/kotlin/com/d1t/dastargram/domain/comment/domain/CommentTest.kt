package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.domain.Member
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class CommentTest : DescribeSpec({

    describe("Comment.create") {
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        context("댓글 내용이 주어졌을 때") {
            it("댓글을 생성한다.") {
                val comment = Comment.create("commentTest", member)
                comment.content shouldBe "commentTest"
            }
        }
    }

    describe("Comment.update") {
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        val comment = Comment.create("commentTest", member)


        context("댓글 내용을 수정하면") {
            it("댓글 내용이 수정된다.") {
                val newContent = "newComment"
                comment.updateComment(newContent)
                comment.content shouldBe newContent
            }
        }

        context("좋아요 수 증가") {
            it("좋아요 수가 증가한다.") {
                comment.increaseLikeCount()
                comment.likeCount shouldBe 1
            }
        }

        context("좋아요 수 감소") {
            it("좋아요 수가 감소한다.") {
                comment.decreaseLikeCount()
                comment.likeCount shouldBe 0
            }
        }

        context("좋아요 수 감소할 때 좋아요 수가 0이면") {
            it("IllegalArgumentException을 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    comment.decreaseLikeCount()
                }
                exception.message shouldBe "좋아요 수는 0보다 작을 수 없습니다."
                comment.likeCount shouldBe 0
            }
        }
    }
})
