package com.d1t.dastargram.domain.post

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class PostTest : DescribeSpec({
    describe("Post.create") {
        val member = Member.create(TEST_EMAIL, TEST_PASSWORD, TEST_NICKNAME, TEST_NAME)
        context("사진과 내용이 주어졌을때") {
            it("게시물을 업로드 한다.") {
                val postImages = listOf("https://www.test.com/",  "https://www.test.com/",  "https://www.test.com/");
                val post = Post.create(member, "test", postImages);
                post.content shouldBe "test"
                post.postImages.map {
                    it shouldBe "https://www.test.com/"
                }
            }
        }

        context("사진과 빈 내용이 주어졌을때") {
            it("게시물을 업로드 한다.") {
                val postImages = listOf("https://www.test.com/",  "https://www.test.com/",  "https://www.test.com/");
                val post = Post.create(member, null, postImages);
                post.content shouldBe null
                post.postImages.map {
                    it shouldBe "https://www.test.com/"
                }
            }
        }

        context("사진을 한장도 업로드하지 않았을때") {
            it("IllegalArgumentException 던진다.") {
                val exception = shouldThrow<IllegalArgumentException> {
                    Post.create(member, "test", emptyList());
                }
                exception.message shouldBe "사진은 1개 이상 10개 이하 등록해야합니다."
            }
        }

        context("사진을 10개 이상 업로드했을때") {
            it("IllegalArgumentException 던진다.") {
                val postImages: MutableList<String> = mutableListOf()
                for(i in 1..11)
                    postImages.add("https://www.test.com/")

                val exception = shouldThrow<IllegalArgumentException> {
                    Post.create(member, "test", postImages);
                }
                exception.message shouldBe "사진은 1개 이상 10개 이하 등록해야합니다."
            }
        }
    }
})