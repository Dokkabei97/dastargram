package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.comment.TestCommentArgument.Companion.TEST_CONTENT
import com.d1t.dastargram.domain.comment.TestCommentArgument.Companion.TEST_POST_ID
import com.d1t.dastargram.domain.comment.TestCommentArgument.Companion.TEST_MEMBER_ID
import com.d1t.dastargram.domain.comment.dto.CommentResponse
import com.d1t.dastargram.domain.member.TestMemberArgument
import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.domain.post.domain.PostReader
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class CommentServiceImplTest : BehaviorSpec ({
    val memberReader: MemberReader = mockk()
    val postReader: PostReader = mockk()
    val commentReader: CommentReader = mockk()
    val commentStore: CommentStore = mockk()
    val commentService = CommentServiceImpl(memberReader, postReader, commentReader, commentStore)

    given("댓글 등록") {
        val commentRequest = CommentRequest.InsertRequest(TEST_CONTENT, TEST_POST_ID, TEST_MEMBER_ID)
        val member = Member.create(
            TestMemberArgument.TEST_EMAIL,
            TestMemberArgument.TEST_PASSWORD,
            TestMemberArgument.TEST_NICKNAME,
            TestMemberArgument.TEST_NAME
        )
        val postImages = listOf("https://www.test.com/",  "https://www.test.com/",  "https://www.test.com/")
        val post = Post.create(member, "test", postImages)
        val comment = Comment.create(TEST_CONTENT, TEST_MEMBER_ID, TEST_POST_ID)
        val commentResponse = CommentResponse.PublicResponse(1, 0, TEST_CONTENT)

        every { memberReader.getMemberById(any()) } returns member
        every { postReader.findById(any()) } returns post
        every { commentStore.create(any()) } returns comment

        `when` ("댓글 등록") {
            val result = commentService.insert(commentRequest)
            
            then ("댓글 등록 성공") {
                result shouldBe commentResponse
            }
        }
    }


})