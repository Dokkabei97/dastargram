package com.d1t.dastargram.domain.follow.domain

import com.d1t.dastargram.domain.follow.dto.FollowRequest.UpdateFollowRequest
import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class FollowServiceImpl(
        val followStore: FollowStore,
        val followReader: FollowReader,
        val memberReader: MemberReader
) : FollowService {

    @Transactional
    override fun follow(request: UpdateFollowRequest): Follow {
        check(!isAlreadyFollow(request)) { "이미 팔로우 중입니다." }

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

    override fun getFollowers(request: Long): List<Follow> {
        val memberId = getMember(request).id!!
        val followers = followReader.getFollowers(memberId)

        if (followers.isEmpty()) {
            return emptyList()
        }
        return followers
    }

    override fun getFollowings(request: Long): List<Follow> {
        val memberId = getMember(request).id!!
        val followings = followReader.getFollowings(memberId)

        if (followings.isEmpty()) {
            return emptyList()
        }
        return followings
    }

    override fun isFollowing(request: UpdateFollowRequest): Boolean {
        TODO("Not yet implemented")
    }

    private fun isAlreadyFollow(request: UpdateFollowRequest) =
            followReader.isExistByFollowerIdAndFollowingId(request.followerMemberId, request.followingMemberId)

    private fun getMember(request: Long) = memberReader.getMemberById(request)

    private fun getMembers(request: UpdateFollowRequest): Pair<Member, Member> {
        val followerMember = memberReader.getMemberById(request.followerMemberId)
        val followingMember = memberReader.getMemberById(request.followingMemberId)
        return Pair(followerMember, followingMember)
    }
}