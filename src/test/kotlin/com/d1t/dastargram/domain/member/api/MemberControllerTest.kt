package com.d1t.dastargram.domain.member.api

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.application.MemberFacade
import com.d1t.dastargram.domain.member.dto.MemberRequest.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders.*

@WebMvcTest(MemberController::class)
@Import(HttpEncodingAutoConfiguration::class)
class MemberControllerTest {
    // @WebMvcTest에 Kotest 사용시 한글 깨짐 이슈 존재
    @MockBean lateinit var memberFacade: MemberFacade
    @Autowired lateinit var mockMvc: MockMvc

    @Test
    fun `회원가입 성공`() {
        val signUpMemberRequest = SignUpMemberRequest(
                TEST_EMAIL,
                TEST_PASSWORD,
                TEST_NICKNAME,
                TEST_NAME
        )

        val json = jacksonObjectMapper().writeValueAsString(signUpMemberRequest)

        mockMvc.perform(post("/api/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json)
                .characterEncoding("utf-8"))
                .andExpect(status().isOk)
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string("{\"result\":\"SUCCESS\",\"data\":null,\"message\":\"회원가입 성공\",\"error\":null}"))
        // TODO 공통 Response 개발 완료 후 Controller Return 바뀌면 수정 필요
    }
}
