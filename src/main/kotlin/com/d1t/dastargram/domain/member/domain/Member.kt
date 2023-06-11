package com.d1t.dastargram.domain.member.domain

import com.d1t.dastargram.global.common.AbstractEntity
import jakarta.persistence.*

@Entity
@Table(name = "members")
class Member(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "member_id")
        val id: Long?,

        @Column(name = "email")
        var email: String,

        @Column(name = "password")
        var password: String,

        @Column(name = "nickname")
        var nickname: String,

        @Column(name = "name")
        var name: String,

        @Column(name = "profile_image")
        var profileImage: String?,

        @Column(name = "follower_count")
        var followerCount: Int = 0,

        @Column(name = "following_count")
        var followingCount: Int = 0,

        ) : AbstractEntity() {

    companion object {
        private const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private const val PASSWORD_REGEX = "^[a-zA-Z0-9!@#$%^&*()?_~]{8,15}$"
        private const val NICKNAME_REGEX = "^[가-힣a-zA-Z0-9]{2,10}$"
        private const val NAME_REGEX = "^[가-힣a-zA-Z]{2,10}$"

        fun create(email: String, password: String, nickname: String, name: String): Member {
            validateEmail(email)
            validatePassword(password)
            validateNickname(nickname)
            validateName(name)
            return Member(null, email, password, nickname, name, null)
        }

        private fun validateEmail(email: String) {
            require(email.isNotBlank()) { "이메일은 필수입니다." }
            check(email.length <= 30) { "이메일은 30자리 이하여야 합니다." }
            check(email.matches(Regex(EMAIL_REGEX))) { "이메일 형식이 올바르지 않습니다." }
        }

        private fun validatePassword(password: String) {
            require(password.isNotBlank()) { "비밀번호는 필수입니다." }
            check(password.length in 8..15) { "비밀번호는 8~15자리여야 합니다." }
            check(password.matches(Regex(PASSWORD_REGEX))) { "비밀번호는 영문 대소문자, 숫자, 특수문자(!@#$%^&*()?_~)로만 구성되어야 합니다." }
        }

        private fun validateNickname(nickname: String) {
            require(nickname.isNotBlank()) { "닉네임은 필수입니다." }
            check(nickname.length in 2..10) { "닉네임은 2~10자리여야 합니다." }
            check(nickname.matches(Regex(NICKNAME_REGEX))) { "닉네임은 한글, 영문, 숫자로만 구성되어야 합니다." }
        }

        private fun validateName(name: String) {
            require(name.isNotBlank()) { "이름은 필수입니다." }
            check(name.length in 2..10) { "이름은 2~10자리여야 합니다." }
            check(name.matches(Regex(NAME_REGEX))) { "이름은 한글, 영문으로만 구성되어야 합니다." }
        }

        private fun validateFollowingCount(followingCount: Int) {
            check(followingCount > 0) { "팔로잉 수는 0보다 작을 수 없습니다." }
        }

        private fun validateFollowerCount(followerCount: Int) {
            check(followerCount > 0) { "팔로워 수는 0보다 작을 수 없습니다." }
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

    fun updateName(name: String) {
        validateName(name)
        this.name = name
    }

    fun updateProfileImage(profileImage: String) {
        this.profileImage = profileImage
    }

    fun increaseFollowerCount() {
        this.followerCount++
    }

    fun decreaseFollowerCount() {
        validateFollowerCount(this.followerCount)
        this.followerCount--
    }

    fun increaseFollowingCount() {
        this.followingCount++
    }

    fun decreaseFollowingCount() {
        validateFollowingCount(this.followingCount)
        this.followingCount--
    }
}