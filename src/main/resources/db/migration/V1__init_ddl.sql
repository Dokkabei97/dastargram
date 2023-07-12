CREATE TABLE MEMBERS
(
    MEMBER_ID       BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '회원번호',
    EMAIL           VARCHAR(255)  NOT NULL COMMENT '이메일',
    PASSWORD        VARCHAR(1000) NOT NULL COMMENT '비밀번호',
    NICKNAME        VARCHAR(255)  NOT NULL COMMENT '닉네임',
    NAME            VARCHAR(255)  NOT NULL COMMENT '이름',
    PROFILE_IMAGE   VARCHAR(500) COMMENT '프로필 이미지',
    FOLLOWER_COUNT  INT          DEFAULT 0 COMMENT '팔로워 수',
    FOLLOWING_COUNT INT          DEFAULT 0 COMMENT '팔로잉 수',
    POST_COUNT      INT          DEFAULT 0 COMMENT '게시물 수',
    ROLE            VARCHAR(255) DEFAULT 'ROLE_MEMBER' COMMENT '권한',
    CREATED_AT      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    UPDATED_AT      TIMESTAMP    DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'MEMBERS' CHARSET = UTF8MB4;

CREATE INDEX MEMBER_IDX01 ON MEMBERS (MEMBER_ID);
CREATE INDEX MEMBER_IDX02 ON MEMBERS (EMAIL);
CREATE INDEX MEMBER_IDX03 ON MEMBERS (NICKNAME);
CREATE INDEX MEMBER_IDX04 ON MEMBERS (NAME);

CREATE UNIQUE INDEX MEMBER_IDX_EMAIL_UNIQUE ON MEMBERS (EMAIL);
CREATE UNIQUE INDEX MEMBER_IDX_NICKNAME_UNIQUE ON MEMBERS (NICKNAME);

CREATE TABLE POSTS
(
    POST_ID     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 번호',
    MEMBER_ID   BIGINT       NOT NULL COMMENT '회원 번호',
    TITLE       VARCHAR(255) NOT NULL COMMENT '제목',
    CONTENT     TEXT         NULL COMMENT '내용',
    POST_IMAGES JSON         NOT NULL COMMENT '게시물 이미지',
    LIKE_COUNT  INT       DEFAULT 0 COMMENT '좋아요 수',
    CREATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    UPDATED_AT  TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'POSTS' CHARSET = UTF8MB4;

CREATE INDEX POST_IDX01 ON POSTS (POST_ID);
CREATE INDEX POST_IDX02 ON POSTS (MEMBER_ID);

CREATE TABLE POST_LIKES
(
    POST_LIKE_ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 좋아요 번호',
    POST_ID      BIGINT NOT NULL COMMENT '게시물 번호',
    MEMBER_ID    BIGINT NOT NULL COMMENT '회원 번호',
    CREATED_AT   TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    UPDATED_AT   TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'POST_LIKES' CHARSET = UTF8MB4;

CREATE INDEX POST_LIKE_IDX01 ON POST_LIKES (POST_LIKE_ID);
CREATE INDEX POST_LIKE_IDX02 ON POST_LIKES (POST_ID);
CREATE INDEX POST_LIKE_IDX03 ON POST_LIKES (MEMBER_ID);

CREATE TABLE POST_COMMENTS
(
    POST_COMMENT_ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 댓글 번호',
    POST_ID         BIGINT NOT NULL COMMENT '게시물 번호',
    MEMBER_ID       BIGINT NOT NULL COMMENT '회원 번호',
    CONTENT         TEXT   NOT NULL COMMENT '내용',
    LIKE_COUNT      INT       DEFAULT 0 COMMENT '좋아요 수',
    CREATED_AT      TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    UPDATED_AT      TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'POST_COMMENTS' CHARSET = UTF8MB4;

CREATE INDEX POST_COMMENT_IDX01 ON POST_COMMENTS (POST_COMMENT_ID);
CREATE INDEX POST_COMMENT_IDX02 ON POST_COMMENTS (POST_ID);
CREATE INDEX POST_COMMENT_IDX03 ON POST_COMMENTS (MEMBER_ID);

CREATE TABLE POST_COMMENT_LIKES
(
    POST_COMMENT_LIKE_ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '게시물 댓글 좋아요 번호',
    POST_COMMENT_ID      BIGINT NOT NULL COMMENT '게시물 댓글 번호',
    MEMBER_ID            BIGINT NOT NULL COMMENT '회원 번호',
    CREATED_AT           TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    UPDATED_AT           TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'POST_COMMENT_LIKES' CHARSET = UTF8MB4;

CREATE INDEX POST_COMMENT_LIKE_IDX01 ON POST_COMMENT_LIKES (POST_COMMENT_LIKE_ID);
CREATE INDEX POST_COMMENT_LIKE_IDX02 ON POST_COMMENT_LIKES (POST_COMMENT_ID);
CREATE INDEX POST_COMMENT_LIKE_IDX03 ON POST_COMMENT_LIKES (MEMBER_ID);

CREATE TABLE BOOKMARK_POSTS
(
    BOOKMARK_POST_ID BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '북마크 게시물 번호',
    POST_ID          BIGINT NOT NULL COMMENT '게시물 번호',
    MEMBER_ID        BIGINT NOT NULL COMMENT '회원 번호',
    CREATED_AT       TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    UPDATED_AT       TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'BOOKMARK_POSTS' CHARSET = UTF8MB4;

CREATE INDEX BOOKMARK_POST_IDX01 ON BOOKMARK_POSTS (BOOKMARK_POST_ID);
CREATE INDEX BOOKMARK_POST_IDX02 ON BOOKMARK_POSTS (POST_ID);
CREATE INDEX BOOKMARK_POST_IDX03 ON BOOKMARK_POSTS (MEMBER_ID);

CREATE TABLE FOLLOWS
(
    FOLLOW_ID           BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '팔로우 회원 번호',
    FOLLOWER_MEMBER_ID  BIGINT NOT NULL COMMENT '팔로워 회원 번호',
    FOLLOWING_MEMBER_ID BIGINT NOT NULL COMMENT '팔로잉 회원 번호',
    CREATED_AT          TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '생성일',
    UPDATED_AT          TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '수정일'
) COMMENT 'FOLLOW_MEMBERS' CHARSET = UTF8MB4;

CREATE INDEX FOLLOW_IDX01 ON FOLLOWS (FOLLOW_ID);
CREATE INDEX FOLLOW_IDX02 ON FOLLOWS (FOLLOWER_MEMBER_ID);
CREATE INDEX FOLLOW_IDX03 ON FOLLOWS (FOLLOWING_MEMBER_ID);