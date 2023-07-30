package com.d1t.dastargram.domain.search.application

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.search.domain.SearchService
import com.d1t.dastargram.domain.search.dto.SearchMemberResponse
import org.springframework.stereotype.Service

@Service
class SearchFacade(val searchService: SearchService) {
    fun getMemberSearchInfo(keyword: String): List<Member> = searchService.getMemberSearchInfo(keyword)
}