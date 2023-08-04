CREATE TABLE members
(
    member_id       BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '회원번호',
    email           VARCHAR(255)  NOT NULL COMMENT '이메일',
    password        VARCHAR(1000) NOT NULL COMMENT '비밀번호',
    nickname        VARCHAR(255)  NOT NULL COMMENT '닉네임',
    name            VARCHAR(255)  NOT NULL COMMENT '이름',
    profile_image   VARCHAR(500) COMMENT '프로필 이미지',
    follower_count  INT          DEFAULT 0 COMMENT '팔로워 수',
    following_count INT          DEFAULT 0 COMMENT '팔로잉 수',
    post_count      INT          DEFAULT 0 COMMENT '게시물 수',
    role            VARCHAR(255) DEFAULT 'ROLE_MEMBER' COMMENT '권한',
    created_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'members' CHARSET = UTF8MB4;

CREATE INDEX member_idx01 ON members (member_id);
CREATE INDEX member_idx02 ON members (email);
CREATE INDEX member_idx03 ON members (nickname);
CREATE INDEX member_idx04 ON members (name);

CREATE UNIQUE INDEX member_idx_email_unique ON members (email);
CREATE UNIQUE INDEX member_idx_nickname_unique ON members (nickname);

CREATE TABLE posts
(
    post_id     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 번호',
    member_id   BIGINT       NOT NULL COMMENT '회원 번호',
    title       VARCHAR(255) NOT NULL COMMENT '제목',
    content     TEXT         NULL COMMENT '내용',
    post_images JSON         NOT NULL COMMENT '게시물 이미지',
    like_count  INT       DEFAULT 0 COMMENT '좋아요 수',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'posts' CHARSET = UTF8MB4;

CREATE INDEX post_idX01 ON posts (post_id);
CREATE INDEX post_idX02 ON posts (member_id);

CREATE TABLE post_likes
(
    post_like_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 좋아요 번호',
    post_id      BIGINT NOT NULL COMMENT '게시물 번호',
    member_id    BIGINT NOT NULL COMMENT '회원 번호',
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'post_likes' CHARSET = UTF8MB4;

CREATE INDEX post_like_idX01 ON post_likes (post_like_id);
CREATE INDEX post_like_idX02 ON post_likes (post_id);
CREATE INDEX post_like_idX03 ON post_likes (member_id);

CREATE TABLE comments
(
    comments_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 댓글 번호',
    post_id     BIGINT NOT NULL COMMENT '게시물 번호',
    member_id   BIGINT NOT NULL COMMENT '회원 번호',
    content     TEXT   NOT NULL COMMENT '내용',
    like_count  INT       DEFAULT 0 COMMENT '좋아요 수',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'comments' CHARSET = UTF8MB4;

CREATE INDEX comments_idX01 ON comments (comments_id);
CREATE INDEX comments_idX02 ON comments (post_id);
CREATE INDEX comments_idX03 ON comments (member_id);

CREATE TABLE comment_likes
(
    comment_like_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 댓글 좋아요 번호',
    comment_id      BIGINT NOT NULL COMMENT '게시물 댓글 번호',
    member_id        BIGINT NOT NULL COMMENT '회원 번호',
    created_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'comment_likes' CHARSET = UTF8MB4;

CREATE INDEX comment_like_idX01 ON comment_likes (comment_like_id);
CREATE INDEX comment_like_idX02 ON comment_likes (comment_id);
CREATE INDEX comment_like_idX03 ON comment_likes (member_id);

CREATE TABLE bookmarks
(
    bookmark_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '북마크 게시물 번호',
    post_id     BIGINT NOT NULL COMMENT '게시물 번호',
    member_id   BIGINT NOT NULL COMMENT '회원 번호',
    created_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'bookmarks' CHARSET = UTF8MB4;

CREATE INDEX bookmark_idX01 ON bookmarks (bookmark_id);
CREATE INDEX bookmark_idX02 ON bookmarks (post_id);
CREATE INDEX bookmark_idX03 ON bookmarks (member_id);

CREATE TABLE follows
(
    follow_id           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '팔로우 회원 번호',
    follower_member_id  BIGINT NOT NULL COMMENT '팔로워 회원 번호',
    following_member_id BIGINT NOT NULL COMMENT '팔로잉 회원 번호',
    created_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    updated_at          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'follows' CHARSET = UTF8MB4;

CREATE INDEX follow_idX01 ON follows (follow_id);
CREATE INDEX follow_idX02 ON follows (follower_member_id);
CREATE INDEX follow_idX03 ON follows (following_member_id);

