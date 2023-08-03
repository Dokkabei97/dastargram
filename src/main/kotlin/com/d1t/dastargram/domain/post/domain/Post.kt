package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.global.common.entity.AbstractEntity
import com.vladmihalcea.hibernate.type.json.JsonType
import jakarta.persistence.*
import org.hibernate.annotations.Type

@Entity
@Table(name = "posts")
class Post(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "post_id")
        val id: Long?,

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "member_id")
        var member: Member,

        @Column(name="post_content", columnDefinition = "text")
        var content: String? = null,

        @Column(name = "like_count")
        var likeCount: Int = 0,

        @Type(JsonType::class)
        @Column(name = "post_images", columnDefinition = "json")
        var postImages: List<String>,

        ) : AbstractEntity() {

    companion object {
        fun create(member: Member, content: String?, postImages: List<String>): Post {
            validatePostImages(postImages);
            return Post(null, member, content, 0, postImages)
        }

        private fun validateLikeCount(likeCount: Int) {
            require(likeCount > 0) { "좋아요 수는 0보다 작을 수 없습니다." }
        }

        private fun validatePostImages(postImages: List<String>) {
            require(postImages.size in 1..10) { "사진은 1개 이상 10개 이하 등록해야합니다." }
        }

    }

    fun validatePostOwner(memberId: Long) {
        require(this.member.id == memberId) { "해당 게시물의 소유자가 아닙니다." }
    }

    fun updateContent(content: String) {
        this.content = content
    }

    fun increaseLikeCount() {
        this.likeCount++
    }

    fun decreaseLikeCount() {
        validateLikeCount(this.likeCount)
        this.likeCount--
    }
}