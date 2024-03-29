package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.dto.PostRequest.*
import com.d1t.dastargram.domain.post.dto.PostResponse.*
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostServiceImpl(
    val postStore: PostStore,
    val postReader: PostReader,
    val memberReader: MemberReader
): PostService {

    @Transactional
    override fun upload(uploadPostRequest: UploadPostRequest): PostPublicResponse {
        //1. find member
        val member = memberReader.getMemberById(uploadPostRequest.memberId)

        //2. post create
        val post = postStore.create(
            Post.create(
                member,
                uploadPostRequest.content,
                uploadPostRequest.postImages
            )
        )

        return PostPublicResponse(
            post.id!!,
            post.content,
            post.likeCount,
            post.postImages
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
            post.id!!,
            post.content,
            post.likeCount,
            post.postImages
        )
    }

    override fun getPosts(memberId: Long): List<PostPublicResponse> {
        //1. check exits member
        memberReader.getMemberById(memberId)

        //2. find post list
        val posts: List<Post> = postReader.findByMemberId(memberId)

        return posts.map {it.toPostPublicResponse()}.toList()
    }
}