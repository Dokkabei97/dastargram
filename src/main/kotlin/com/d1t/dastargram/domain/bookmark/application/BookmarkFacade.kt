package com.d1t.dastargram.domain.bookmark.application

import com.d1t.dastargram.domain.bookmark.domain.BookmarkService
import com.d1t.dastargram.domain.bookmark.dto.BookmarkRequest
import org.springframework.stereotype.Service

@Service
class BookmarkFacade(val bookmarkService: BookmarkService) {

    fun selectAll(selectRequest: BookmarkRequest.SelectRequest) = bookmarkService.selectAll(selectRequest)
    fun insert(insertRequest: BookmarkRequest.InsertRequest) = bookmarkService.insert(insertRequest)
    fun delete(deleteRequest: BookmarkRequest.DeleteRequest) = bookmarkService.delete(deleteRequest)
}