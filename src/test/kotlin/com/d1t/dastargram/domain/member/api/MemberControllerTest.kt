package com.d1t.dastargram.domain.member.api

import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_EMAIL
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_NICKNAME
import com.d1t.dastargram.domain.member.TestMemberArgument.Companion.TEST_PASSWORD
import com.d1t.dastargram.domain.member.application.MemberFacade
import com.d1t.dastargram.domain.member.dto.MemberRequest.*
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import io.kotest.core.spec.style.AnnotationSpec
import io.mockk.Runs
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import org.springframework.boot.autoconfigure.web.servlet.HttpEncodingAutoConfiguration
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@WebMvcTest(MemberController::class)
@Import(HttpEncodingAutoConfiguration::class)
class MemberControllerTest : AnnotationSpec() {
    private val memberFacade = mockk<MemberFacade>()
    private val mockMvc: MockMvc = MockMvcBuilders.standaloneSetup(MemberController(memberFacade)).build()

    @Test
    fun `회원가입 성공`() {
        val signUpMemberRequest = SignUpMemberRequest(
                TEST_EMAIL,
                TEST_PASSWORD,
                TEST_NICKNAME,
                TEST_NAME
        )

        every { memberFacade.signUp(signUpMemberRequest) } just Runs

        mockMvc.perform(post("/api/v1/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonObjectMapper().writeValueAsString(signUpMemberRequest))
                .characterEncoding("utf-8"))
                .andExpect(status().isOk)
                .andExpect(content().string("회원가입 성공"))
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.message").value("회원가입 성공"))
        // TODO:  테스트 실제 결과값이 ???? ??로 나와서 실패함
    }
}
