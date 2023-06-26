package com.d1t.dastargram.domain.post.infrastructure

import com.d1t.dastargram.domain.post.domain.PostReader
import org.springframework.stereotype.Component

@Component
class PostReaderImpl(val postRepository: PostRepository) : PostReader {
}