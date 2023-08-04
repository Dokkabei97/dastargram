package com.d1t.dastargram.domain.follow.domain

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.shouldBe

class FollowTest : DescribeSpec({

    describe("create") {
        it("should create a new Follow") {
            val followerMemberId = 1L
            val followingMemberId = 2L
            val follow = Follow.create(followerMemberId, followingMemberId)

            follow.followerMemberId shouldBe followerMemberId
            follow.followingMemberId shouldBe followingMemberId
        }
    }
})
