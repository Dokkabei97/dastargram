package com.d1t.dastargram.domain.follow.infrastructure

import com.d1t.dastargram.domain.follow.domain.FollowReader
import org.springframework.stereotype.Component

@Component
class FollowReaderImpl(val followReader: FollowReader) : FollowReader