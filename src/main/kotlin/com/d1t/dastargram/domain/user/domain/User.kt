package com.d1t.dastargram.domain.user.domain

import com.d1t.dastargram.global.common.AbstractEntity
import jakarta.persistence.*

@Entity
@Table(name = "users")
class User(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "user_id")
        val id: Long?,

        @Column(name = "email")
        var email: String,

        @Column(name = "password")
        var password: String,

        @Column(name = "nickname")
        var nickname: String,

        ) : AbstractEntity() {
    companion object {
        fun create(email: String, password: String, nickname: String): User {
            validateEmail(email)
            validatePassword(password)
            validateNickname(nickname)
            return User(null, email, password, nickname)
        }

        private fun validateEmail(email: String) {
            require(email.isNotBlank()) { "이메일은 필수입니다." }
            check(email.length <= 30) { "이메일은 30자리 이하여야 합니다." }
            check(email.matches(Regex("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"))) { "이메일 형식이 올바르지 않습니다." }
        }

        private fun validatePassword(password: String) {
            require(password.isNotBlank()) { "비밀번호는 필수입니다." }
            check(password.length in 8..15) { "비밀번호는 8~15자리여야 합니다." }
            check(password.matches(Regex("^[a-zA-Z0-9!@#$%^&*()?_~]{8,15}$"))) { "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#$%^&*()?_~)로만 구성되어야 합니다." }
        }

        private fun validateNickname(nickname: String) {
            require(nickname.isNotBlank()) { "닉네임은 필수입니다." }
            check(nickname.length in 2..10) { "닉네임은 2~10자리여야 합니다." }
            check(nickname.matches(Regex("^[가-힣a-zA-Z0-9]{2,10}$"))) { "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다." }
        }
    }

    fun updatePassword(password: String) {
        validatePassword(password)
        this.password = password
    }

    fun updateNickname(nickname: String) {
        validateNickname(nickname)
        this.nickname = nickname
    }
}