package com.d1t.dastargram.domain.user.infrastructure

import com.d1t.dastargram.domain.user.domain.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long>