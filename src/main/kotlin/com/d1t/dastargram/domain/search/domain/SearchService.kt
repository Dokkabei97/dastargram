package com.d1t.dastargram.domain.search.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.search.dto.SearchMemberResponse


interface SearchService {
    fun getMemberSearchInfo(keyword: String): List<Member>

}