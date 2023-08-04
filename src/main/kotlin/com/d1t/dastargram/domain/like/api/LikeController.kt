package com.d1t.dastargram.domain.like.api

import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/likes")
class LikeController {
    @GetMapping
    fun index(): String {
        return "LIKE";
    }

}