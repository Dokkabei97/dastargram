package com.d1t.dastargram.domain.bookmark.domain

interface BookmarkReader {

    fun getBookmarks(memberId: Long): List<Bookmark>
    fun getBookmarkById(bookmarkId: Long): Bookmark
}