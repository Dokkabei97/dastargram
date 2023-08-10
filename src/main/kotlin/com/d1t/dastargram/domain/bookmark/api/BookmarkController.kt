package com.d1t.dastargram.domain.bookmark.api

import com.d1t.dastargram.domain.bookmark.application.BookmarkFacade
import com.d1t.dastargram.domain.bookmark.dto.BookmarkRequest
import com.d1t.dastargram.domain.bookmark.dto.BookmarkRequest.*
import com.d1t.dastargram.domain.bookmark.dto.BookmarkResponse
import com.d1t.dastargram.global.common.response.CommonResponse
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/bookmarks")
class BookmarkController (val bookmarkFacade: BookmarkFacade) {

    @GetMapping
    fun getBookmarks(@RequestBody @Validated selectRequest: BookmarkRequest.SelectRequest) : CommonResponse<List<BookmarkResponse>> {
        val bookmarks = bookmarkFacade.selectAll(selectRequest)
        return CommonResponse.success(bookmarks, "북마크 전체 조회 성공")
    }

    @PostMapping
    fun createBookmark(@RequestBody @Validated insertRequest: BookmarkRequest.InsertRequest) : CommonResponse<BookmarkResponse> {
        val bookmark = bookmarkFacade.insert(insertRequest)
        return CommonResponse.success(bookmark, "북마크 등록 성공")
    }

    @DeleteMapping
    fun deleteBookmark(@RequestBody @Validated deleteRequest: BookmarkRequest.DeleteRequest) : CommonResponse<Unit?> {
        bookmarkFacade.delete(deleteRequest)
        return CommonResponse.success(null, "북마크 해제 성공")
    }
}