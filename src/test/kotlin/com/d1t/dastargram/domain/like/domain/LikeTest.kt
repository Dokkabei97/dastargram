package com.d1t.dastargram.domain.like.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe

class LikeTest: DescribeSpec ({

    describe("Like.create") {
        context("회원과 게시물이 있을 때") {
            it("좋아요를 생성한다") {
                val postImages = listOf("https://www.test.com/",  "https://www.test.com/",  "https://www.test.com/")
                val member = Member.create("example@naver.com", "password", "별명", "이름")
                val post = Post.create(member, "글 내용입니다", postImages)
                val like = Like.create(member, post)
            }
        }
    }
})