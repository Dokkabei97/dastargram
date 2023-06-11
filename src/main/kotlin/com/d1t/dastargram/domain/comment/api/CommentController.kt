package com.d1t.dastargram.domain.comment.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/v1/comments")
class CommentController {
    @GetMapping
    fun getComment(): String {
        return "comment"
    }
}
