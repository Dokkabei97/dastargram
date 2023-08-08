package com.d1t.dastargram.domain.bookmark.api

import com.d1t.dastargram.domain.bookmark.TestBookmarkArgument.Companion.TEST_MEMBER_ID
import com.d1t.dastargram.domain.bookmark.TestBookmarkArgument.Companion.TEST_POST_ID
import com.d1t.dastargram.domain.bookmark.application.BookmarkFacade
import com.d1t.dastargram.domain.bookmark.dto.BookmarkRequest
import com.d1t.dastargram.domain.member.api.MemberController
import com.d1t.dastargram.global.config.security.SecurityConfig
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.FilterType
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [BookmarkController::class],
    excludeFilters = [
        ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = [SecurityConfig::class])
    ]
)
@Import(HttpEncodingAutoConfiguration::class)
class BookmarkControllerTest {
    // @WebMvcTest에 Kotest 사용시 한글 깨짐 이슈 존재
    @MockBean
    lateinit var bookmarkFacade: BookmarkFacade

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    @WithMockUser
    fun `북마크 등록 성공`() {
        val bookmarkInsertRequest = BookmarkRequest.InsertRequest(
            TEST_POST_ID,
            TEST_MEMBER_ID
        )

        val json = jacksonObjectMapper().writeValueAsString(bookmarkInsertRequest)

        mockMvc.perform(post("/api/v1/bookmarks")
            .contentType(MediaType.APPLICATION_JSON)
            .with(csrf())
            .content(json)
            .characterEncoding("utf-8"))
            .andExpect(status().isOk)
            .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
            .andExpect(content().string("{\"result\":\"SUCCESS\",\"data\":null,\"message\":\"북마크 등록 성공\",\"errorCode\":null}"))
    }
}