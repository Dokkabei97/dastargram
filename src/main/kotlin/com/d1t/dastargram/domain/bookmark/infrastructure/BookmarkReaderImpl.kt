package com.d1t.dastargram.domain.bookmark.infrastructure

import com.d1t.dastargram.domain.bookmark.domain.Bookmark
import com.d1t.dastargram.domain.bookmark.domain.BookmarkReader
import jakarta.persistence.EntityNotFoundException
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class BookmarkReaderImpl(val bookmarkRepository: BookmarkRepository) :BookmarkReader {

    override fun getBookmarks(memberId: Long): List<Bookmark> = bookmarkRepository.findByMemberId(memberId)

    override fun getBookmarkById(bookmarkId: Long) : Bookmark = bookmarkRepository.findByIdOrNull(bookmarkId)
        ?: throw EntityNotFoundException("존재하지 않는 북마크입니다.")

}