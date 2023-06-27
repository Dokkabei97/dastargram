package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostServiceImpl(val postStore: PostStore, val postReader: PostReader) : PostService {

    override fun upload(member: Member, postUploadRequest: UploadPostRequest): Post {
        return postStore.create(
            Post.create(
                member,
                postUploadRequest.content,
                postUploadRequest.postImages
            )
        );
    }

    @Transactional
    override fun delete(memberId: Long, deletePostRequest: DeletePostRequest) {
        //1. check my post
        val post = postReader.findById(deletePostRequest.postId)
        if(memberId != post.member.id) {
            throw RuntimeException("해당 게시물의 소유자가 아닙니다.")
        }

        //2. delete post
        postStore.deleteById(deletePostRequest.postId)
    }

    @Transactional
    override fun update(memberId: Long, updatePostRequest: UpdatePostRequest): PostPublicResponse {
        //1. check my post
        val post = postReader.findById(updatePostRequest.postId)
        if(memberId != post.member.id) {
            throw RuntimeException("해당 게시물의 소유자가 아닙니다.")
        }
        //2. update post
        post.apply {
            updatePostRequest.content?.let { updateContent(it) }
        }

        //3. return response
        return PostPublicResponse(
            post.id,
            post.content,
            post.likeCount,
            post.postImages
        )
    }
}
