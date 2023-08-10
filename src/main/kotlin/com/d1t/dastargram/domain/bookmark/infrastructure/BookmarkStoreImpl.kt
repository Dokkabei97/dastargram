package com.d1t.dastargram.domain.bookmark.infrastructure

import com.d1t.dastargram.domain.bookmark.domain.Bookmark
import com.d1t.dastargram.domain.bookmark.domain.BookmarkStore
import org.springframework.stereotype.Component

@Component
class BookmarkStoreImpl(val bookmarkRepository: BookmarkRepository) : BookmarkStore {

    override fun create(bookmark: Bookmark) = bookmarkRepository.save(bookmark)
    override fun deleteById(bookmarkId: Long) = bookmarkRepository.deleteById(bookmarkId)

}