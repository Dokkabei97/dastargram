package com.d1t.dastargram.domain.postLike.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.global.common.entity.AbstractEntity
import jakarta.persistence.*

@Entity
@Table(name = "post_likes")
class PostLike(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_like_id")
    val id: Long?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val post: Post

) : AbstractEntity() {
    companion object {
        fun create(member: Member, post: Post): PostLike {
            return PostLike(null, member, post)
        }
        
    }
}
