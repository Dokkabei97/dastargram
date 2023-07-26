package com.d1t.dastargram.domain.search.domain

import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.member.dto.MemberResponse
import com.d1t.dastargram.domain.search.dto.SearchMemberResponse
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional()
class SearchServiceImpl (
    val memberReader: MemberReader
) : SearchService {
    override fun getMemberSearchInfo(keyword: String): List<SearchMemberResponse> {
        println("keyword:$keyword")
        val memberSearchInfo = memberReader.getMemberByNicknameContaining(keyword)

        return memberSearchInfo
    }
}