package com.d1t.dastargram.domain.follow.domain

import com.d1t.dastargram.global.common.entity.AbstractEntity
import jakarta.persistence.*

@Entity
@Table(name = "follows")
class Follow(

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "follow_id")
        val id: Long?,

        @Column(name = "follower_member_id")
        var followerMemberId: Long,

        @Column(name = "following_member_id")
        var followingMemberId: Long,
) : AbstractEntity() {

        companion object {
                fun create(followerMemberId: Long, followingMemberId: Long): Follow {
                        return Follow(null, followerMemberId, followingMemberId)
                }
        }
}