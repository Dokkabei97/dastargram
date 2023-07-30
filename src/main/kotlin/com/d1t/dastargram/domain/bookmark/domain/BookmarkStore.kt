package com.d1t.dastargram.domain.bookmark.domain


interface BookmarkStore {

    fun create(bookmark: Bookmark) : Bookmark
    fun deleteById(bookmarkId : Long)
}