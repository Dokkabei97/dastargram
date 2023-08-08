package com.d1t.dastargram.domain.bookmark.domain

import com.d1t.dastargram.domain.bookmark.dto.BookmarkRequest
import com.d1t.dastargram.domain.bookmark.dto.BookmarkResponse
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.domain.PostReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BookmarkServiceImpl(
    val memberReader: MemberReader,
    val bookmarkStore: BookmarkStore,
    val postReader: PostReader,
    val bookmarkReader : BookmarkReader,
) : BookmarkService {

    override fun selectAll(selectRequest: BookmarkRequest.SelectRequest): List<BookmarkResponse> {
        val member = memberReader.getMemberById(selectRequest.memberId)
        val bookmarks = bookmarkReader.getBookmarks(selectRequest.memberId)
        return bookmarks.map { BookmarkResponse(it.id!!, it.post.id!!) }.toList()
    }

    @Transactional
    override fun insert(insertRequest: BookmarkRequest.InsertRequest): BookmarkResponse {
        val member = memberReader.getMemberById(insertRequest.memberId)
        val post = postReader.findById(insertRequest.postId)
        val bookmark = bookmarkStore.create(
            Bookmark.create(
                member,
                post
            )
        )
        return BookmarkResponse(
            bookmark.id!!,
            post.id!!
        )
    }

    @Transactional
    override fun delete(deleteRequest: BookmarkRequest.DeleteRequest) {
        // 회원, 북마크 존재하는지 확인
        val member = memberReader.getMemberById(deleteRequest.memberId)
        val bookmark = bookmarkReader.getBookmarkById(deleteRequest.bookmarkId)
        bookmarkStore.deleteById(deleteRequest.bookmarkId)
    }

}