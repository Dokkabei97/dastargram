package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.domain.Post
import com.d1t.dastargram.global.common.entity.AbstractEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.FetchType
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import jakarta.persistence.Table

/**
 * 댓글 Entity
 */
@Entity
@Table(name = "comments")
class Comment(
    // 댓글 id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id")
    val id: Long?,

    // 댓글 내용
    @Column(name = "comment_content")
    var content: String,

    // 댓글 생성한 member
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    var member: Member,

    //게시글 정보
    @ManyToOne(fetch = FetchType.LAZY)
    @Column(name = "post_id")
    var post: Post,

    @Column(name = "comment_like_count", columnDefinition = "int default 0")
    var likeCount: Int = 0

) : AbstractEntity() {
    companion object {

        fun create(content: String, member: Member, post: Post): Comment {
            validateComment(content)
            return Comment(null, content, member, post)
        }

        private fun validateComment(content: String) {
            require(content.isNotBlank()) { "댓글 내용은 필수입니다." }
            require(content.length <= 255) { "댓글 내용은 255자 이하여야 합니다." }
        }

        private fun validateLikeCount(likeCount: Int) {
            require(likeCount > 0) { "좋아요 수는 0보다 작을 수 없습니다." }
        }
    }

    fun updateContent(content: String) {
        this.content = content
    }

    // 좋아요 감소
    fun decreaseLikeCount() {
        validateLikeCount(this.likeCount)
        this.likeCount--
    }

    // 좋아요 증가
    fun increaseLikeCount() {
        this.likeCount++
    }

}