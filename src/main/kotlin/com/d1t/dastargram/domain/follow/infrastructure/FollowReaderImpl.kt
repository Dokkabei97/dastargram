package com.d1t.dastargram.domain.follow.infrastructure

import com.d1t.dastargram.domain.follow.domain.Follow
import com.d1t.dastargram.domain.follow.domain.FollowReader
import org.springframework.stereotype.Component

@Component
class FollowReaderImpl(val followRepository: FollowRepository) : FollowReader {
    override fun getFollowingsById(id: Long):List<Follow> {
        return followRepository.findByFollowerMemberId(id)
    }
}