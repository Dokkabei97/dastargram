package com.d1t.dastargram.domain.follow.domain

import com.d1t.dastargram.domain.follow.dto.FollowRequest
import com.d1t.dastargram.domain.follow.dto.FollowRequest.*
import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FollowServiceImpl(
        val followStore: FollowStore,
        val memberReader: MemberReader
) : FollowService {

    @Transactional
    override fun follow(request: UpdateFollowRequest): Follow {
        val (followerMember, followingMember) = getMembers(request)
        followerMember.increaseFollowerCount()
        followingMember.increaseFollowingCount()

        val follow = Follow.create(request.followerMemberId, request.followingMemberId)
        return followStore.create(follow)
    }

    @Transactional
    override fun unfollow(request: UpdateFollowRequest) {
        val (followerMember, followingMember) = getMembers(request)
        followerMember.decreaseFollowerCount()
        followingMember.decreaseFollowingCount()

        followStore.deleteByFollowerIdAndFollowingId(request.followerMemberId, request.followingMemberId)
    }

    override fun getFollowers(memberId: Long): List<Follow> {
        TODO("Not yet implemented")
    }

    override fun getFollowings(memberId: Long): List<Follow> {
        TODO("Not yet implemented")
    }

    override fun isFollowing(followerMemberId: Long, followingMemberId: Long): Boolean {
        TODO("Not yet implemented")
    }

    private fun getMembers(request: FollowRequest): Pair<Member, Member> {
        val followerMember = memberReader.getMemberById(request.followerMemberId)
        val followingMember = memberReader.getMemberById(request.followingMemberId)
        return Pair(followerMember, followingMember)
    }
}