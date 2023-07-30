package com.d1t.dastargram.domain.search.domain

import com.d1t.dastargram.domain.member.domain.Member
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
    override fun getMemberByKeyword(keyword: String): List<Member> {
        println("keyword:$keyword")
        val memberSearchInfo = memberReader.getMemberByKeyword(keyword)

        //함께 팔로우 하는 id 찾기

        println(memberSearchInfo)
        return memberSearchInfo
    }
}