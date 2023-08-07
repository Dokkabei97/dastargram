package com.d1t.dastargram.domain.comment.domain

import com.d1t.dastargram.domain.comment.dto.CommentRequest
import com.d1t.dastargram.domain.comment.dto.CommentResponse
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.domain.PostReader
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class CommentServiceImpl(
    val memberReader: MemberReader,
    val postReader: PostReader,
    val commentReader: CommentReader,
    val commentStore: CommentStore
) : CommentService {

    @Transactional
    override fun insert(insertRequest: CommentRequest.InsertRequest): CommentResponse.PublicResponse {
        // 회원과 게시글이 존재하는지 확인
        memberReader.getMemberById(insertRequest.memberId)
        postReader.findById(insertRequest.postId)

        val comment = commentStore.create(
                Comment.create(
                    insertRequest.content,
                    insertRequest.memberId,
                    insertRequest.postId
                )
        )

        return CommentResponse.PublicResponse(
            comment.id!!,
            comment.likeCount,
            comment.content
        )
    }

    @Transactional
    override fun update(updateRequest: CommentRequest.UpdateRequest): CommentResponse.PublicResponse {
        // 회원, 댓글이 존재하는지 확인
        val member = memberReader.getMemberById(updateRequest.memberId)
        val comment = commentReader.getCommentById(updateRequest.id)

        check (member.id != comment.memberId) {
           "요청 id와 댓글 게시자의 id가 일치하지 않습니다."
        }

        comment.apply {
            updateContent(updateRequest.content)
        }

        return CommentResponse.PublicResponse(
            comment.id!!,
            comment.likeCount,
            comment.content
        )
    }

    @Transactional
    override fun delete(deleteRequest: CommentRequest.DeleteRequest) {
        // 회원, 댓글이 존재하는지 확인
        val member = memberReader.getMemberById(deleteRequest.memberId)
        val comment = commentReader.getCommentById(deleteRequest.id)

        check (member.id != comment.memberId) {
            "요청 id와 댓글 게시자의 id가 일치하지 않습니다."
        }

        commentStore.deleteById(deleteRequest.id)
    }


}