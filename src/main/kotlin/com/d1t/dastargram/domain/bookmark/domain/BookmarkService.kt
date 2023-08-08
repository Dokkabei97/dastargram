package com.d1t.dastargram.domain.bookmark.domain


import com.d1t.dastargram.domain.bookmark.dto.BookmarkRequest
import com.d1t.dastargram.domain.bookmark.dto.BookmarkResponse

interface BookmarkService {

    fun selectAll(selectRequest : BookmarkRequest.SelectRequest) : List<BookmarkResponse>
    fun insert(insertRequest: BookmarkRequest.InsertRequest): BookmarkResponse
    fun delete(deleteRequest: BookmarkRequest.DeleteRequest)
}