package com.d1t.dastargram.domain.like.api

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/v1/likes")
class LikeController {
    @GetMapping
    fun index(): String {
        return "LIKE";
    }

}