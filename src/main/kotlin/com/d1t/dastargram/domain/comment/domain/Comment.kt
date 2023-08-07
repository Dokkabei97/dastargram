package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.global.common.entity.AbstractEntity
import jakarta.persistence.*

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
    @Column(name = "member_id")
    var memberId: Long,

    //게시글 정보
    @Column(name = "post_id")
    var postId: Long,

    @Column(name = "comment_like_count")
    var likeCount: Int = 0

) : AbstractEntity() {
    companion object {

        fun create(content: String, memberId: Long, postId: Long): Comment {
            validateComment(content)
            return Comment(null, content, memberId, postId)
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