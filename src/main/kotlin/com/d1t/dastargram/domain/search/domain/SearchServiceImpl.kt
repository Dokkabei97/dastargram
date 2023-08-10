package com.d1t.dastargram.domain.search.domain

import com.d1t.dastargram.domain.follow.domain.FollowReader
import com.d1t.dastargram.domain.follow.domain.FollowService
import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberReader
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
@Transactional()
class SearchServiceImpl (
    val memberReader: MemberReader,
    var followService: FollowService
) : SearchService {
    override fun getMemberByKeyword(keyword: String): List<Member> {
        println("keyword:$keyword")
        val memberSearchInfo = memberReader.getMemberByKeyword(keyword)
        println(memberSearchInfo)

        //함께 팔로우 하는 id 찾기
        // 현재 로그인 id 조회
        //var accessedId = getMemberId()
        val accessedId = 3
        // 나의 팔로잉 찾기
        val followings = followService.getFollowings(accessedId.toLong())
        // 검색 상대의 팔로우 찾기
        return memberSearchInfo
    }
}