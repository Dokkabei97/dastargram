package com.d1t.dastargram.domain.bookmark.domain


import com.d1t.dastargram.domain.bookmark.TestBookmarkArgument
import com.d1t.dastargram.domain.bookmark.TestBookmarkArgument.Companion.TEST_POST_ID
import com.d1t.dastargram.domain.bookmark.TestBookmarkArgument.Companion.TEST_MEMBER_ID
import com.d1t.dastargram.domain.bookmark.dto.BookmarkRequest
import com.d1t.dastargram.domain.bookmark.dto.BookmarkResponse
import com.d1t.dastargram.domain.member.TestMemberArgument
import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.domain.post.domain.PostReader
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk

class BookmarkServiceImplTest : BehaviorSpec({
    val memberReader: MemberReader = mockk()
    val bookmarkStore: BookmarkStore = mockk()
    val postReader: PostReader = mockk()
    val bookmarkReader : BookmarkReader = mockk()
    val bookmarkService = BookmarkServiceImpl(memberReader, bookmarkStore, postReader, bookmarkReader)

    given("북마크 등록") {
        val member = Member.create(
            TestMemberArgument.TEST_EMAIL,
            TestMemberArgument.TEST_PASSWORD,
            TestMemberArgument.TEST_NICKNAME,
            TestMemberArgument.TEST_NAME
        )
        val postImages = listOf("https://www.test.com/",  "https://www.test.com/",  "https://www.test.com/")
        val post = Post.create(member, "test", postImages)

        val bookmark = Bookmark.create(
            member,
            post
        )
        val bookmarkResponse = BookmarkResponse(0, TEST_POST_ID)

        every { memberReader.getMemberById(any()) } returns member
        every { postReader.findByMemberId(any()) } returns listOf(post)
        every { bookmarkStore.create(any()) } returns bookmark

        `when` ("북마크 등록") {
            val result = bookmarkService.insert(BookmarkRequest.InsertRequest(TEST_POST_ID, TEST_MEMBER_ID))

            then ("북마크 등록 성공") {
                result shouldBe bookmarkResponse
            }
        }
    }
})