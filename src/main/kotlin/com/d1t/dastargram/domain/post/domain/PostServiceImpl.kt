package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.Member
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostServiceImpl(
    val postStore: PostStore,
    val postReader: PostReader
) : PostService {

    @Transactional
    override fun upload(member: Member, content: String?, postImages: List<String>): Post {
        return postStore.create(
            Post.create(
                member,
                content,
                postImages
            )
        )
    }

    @Transactional
    override fun delete(deletePostRequest: DeletePostRequest) {
        //1. check my post
        val post = postReader.findById(deletePostRequest.postId)
        post.validatePostOwner(deletePostRequest.memberId)

        //2. delete post
        postStore.deleteById(deletePostRequest.postId)
    }

    @Transactional
    override fun update(updatePostRequest: UpdatePostRequest): PostPublicResponse {
        //1. check my post
        val post = postReader.findById(updatePostRequest.postId)
        post.validatePostOwner(updatePostRequest.memberId)

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