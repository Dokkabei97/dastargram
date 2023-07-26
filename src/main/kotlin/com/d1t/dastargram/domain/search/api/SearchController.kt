package com.d1t.dastargram.domain.search.api

import com.d1t.dastargram.domain.search.application.SearchFacade
import com.d1t.dastargram.domain.search.dto.SearchMemberResponse
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/search")
class SearchController(val searchFacade: SearchFacade) {
    @GetMapping("/{keyword}")
    fun getMemberSearchInfo(
        @PathVariable("keyword") keyword: String
    ): List<SearchMemberResponse>{

        return searchFacade.getMemberSearchInfo(keyword)
    }
}