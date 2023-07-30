package com.d1t.dastargram.domain.bookmark.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.global.common.entity.AbstractEntity
import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "bookmarks")
class Bookmark(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bookmark_id", nullable = false, updatable = false, unique = true)
    val id: Long?,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    val post: Post
) : AbstractEntity() {

    companion object {
        fun create(member: Member, post: Post) : Bookmark {
            return Bookmark(null, member, post);
        }
    }
}