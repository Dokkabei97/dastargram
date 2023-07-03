package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.comment.dto.CommentResponse
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.domain.PostReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentServiceImpl(val memberReader: MemberReader, val postReader: PostReader, val commentReader: CommentReader, val commentStore: CommentStore) : CommentService {

    @Transactional
    override fun insert(insertRequest: CommentRequest.insertRequest): CommentResponse {
        // 회원과 게시글이 존재하는지 확인
        val member = memberReader.findById(insertRequest.memberId)
        val post = postReader.findById(insertRequest.postId)

        val comment = commentStore.create(
                Comment.create(
                    insertRequest.content,
                        member,
                        post
                )
        )

        return CommentResponse(
            comment.likeCount,
            comment.content
        )
    }

    @Transactional
    override fun update(updateRequest: CommentRequest.updateRequest): CommentResponse {
        // 회원, 댓글이 존재하는지 확인
        val member = memberReader.findById(updateRequest.memberId)
        val comment = commentReader.findById(updateRequest.id)

        if (member.id != comment.member.id) {
            throw IllegalArgumentException("요청 id와 댓글 게시자의 id가 일치하지 않습니다.")
        }

        comment.apply {
            updateContent(updateRequest.content)
        }

        return CommentResponse(
            comment.likeCount,
            comment.content
        )
    }

    @Transactional
    override fun delete(deleteRequest: CommentRequest.deleteRequest) {
        // 회원, 댓글이 존재하는지 확인
        val member = memberReader.findById(deleteRequest.memberId)
        val comment = commentReader.findById(deleteRequest.id)

        if (member.id != comment.member.id) {
            throw IllegalArgumentException("요청 id와 댓글 게시자의 id가 일치하지 않습니다.")
        }

        commentStore.deleteById(deleteRequest.id)
    }


}