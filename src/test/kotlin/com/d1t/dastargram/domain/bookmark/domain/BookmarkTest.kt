package com.d1t.dastargram.domain.bookmark.domain

import com.d1t.dastargram.domain.comment.domain.Comment
import com.d1t.dastargram.domain.member.TestMemberArgument
import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class BookmarkTest: DescribeSpec({

    describe("Bookmark.create") {
        val member = Member.create(
            TestMemberArgument.TEST_EMAIL,
            TestMemberArgument.TEST_PASSWORD,
            TestMemberArgument.TEST_NICKNAME,
            TestMemberArgument.TEST_NAME
        )

        val postImages = listOf("https://www.test.com/",  "https://www.test.com/",  "https://www.test.com/")
        val post = Post.create(member, "test", postImages)

        context("POST 글을 북마크 한다") {
            it("북마크를 생성한다.") {
                val bookmark = Bookmark(null, member, post)
                bookmark.post.id shouldBe post.id
            }
        }
    }
})