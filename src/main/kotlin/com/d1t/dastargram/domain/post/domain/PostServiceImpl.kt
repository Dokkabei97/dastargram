package com.d1t.dastargram.domain.post.domain

import com.d1t.dastargram.domain.member.domain.MemberReader
import com.d1t.dastargram.domain.post.dto.PostRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class PostServiceImpl(val postStore: PostStore, val memberReader: MemberReader) : PostService {

    @Transactional
    override fun upload(memberId: Long, postUploadRequest: PostUploadRequest): Post {
        //1. find member
        val member = memberReader.findById(memberId)

        //2. post create
        return postStore.create(
            Post.create(
                member,
                postUploadRequest.content,
                postUploadRequest.postImages
            )
        );
    }

    @Transactional
    override fun delete(deletePostRequest: PostRequest.DeletePostRequest): Post {
        //1. find member
        val member = memberReader.findById(memberId)

        //2. post create
        return postStore.create(
            Post.create(
                member,
                postUploadRequest.content,
                postUploadRequest.postImages
            )
        );
    }
}
