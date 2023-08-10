package com.d1t.dastargram.domain.bookmark.infrastructure

import com.d1t.dastargram.domain.bookmark.domain.Bookmark
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository : JpaRepository<Bookmark, Long> {
    fun findByMemberId(memberId: Long): List<Bookmark>
}