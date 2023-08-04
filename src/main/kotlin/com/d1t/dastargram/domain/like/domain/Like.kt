package com.d1t.dastargram.domain.like.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.global.common.entity.AbstractEntity
import jakarta.persistence.*

@Entity
@Table(name = "likes")
class Like(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "like_id")
    val id: Long?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    val member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val post: Post

) : AbstractEntity() {
    companion object {
        fun create(member: Member, post: Post): Like {
            return Like(null, member, post)
        }
        
    }
}
