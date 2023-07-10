create table members
(
    member_id       bigint auto_increment primary key comment '회원번호',
    email           varchar(255)  not null comment '이메일',
    password        varchar(1000) not null comment '비밀번호',
    nickname        varchar(255)  not null comment '닉네임',
    name            varchar(255)  not null comment '이름',
    profile_image   varchar(500) comment '프로필 이미지',
    follower_count  int          default 0 comment '팔로워 수',
    following_count int          default 0 comment '팔로잉 수',
    post_count      int          default 0 comment '게시물 수',
    role            varchar(255) default 'ROLE_MEMBER' comment '권한',
    create_date     timestamp    default current_timestamp comment '생성일',
    update_date     timestamp    default current_timestamp on update current_timestamp comment '수정일'
) comment 'members' charset = utf8mb4;

create index member_idx01 on members (member_id);
create index member_idx02 on members (email);
create index member_idx03 on members (nickname);
create index member_idx04 on members (name);

create unique index member_idx_email_unique on members (email);
create unique index member_idx_nickname_unique on members (nickname);

create table posts
(
    post_id     bigint auto_increment primary key comment '게시물 번호',
    member_id   bigint       not null comment '회원 번호',
    title       varchar(255) not null comment '제목',
    content     text         null comment '내용',
    post_images json         not null comment '게시물 이미지',
    like_count  int       default 0 comment '좋아요 수',
    create_date timestamp default current_timestamp comment '생성일',
    update_date timestamp default current_timestamp on update current_timestamp comment '수정일'
) comment 'posts' charset = utf8mb4;

create index post_idx01 on posts (post_id);
create index post_idx02 on posts (member_id);

create table post_likes
(
    post_like_id bigint auto_increment primary key comment '게시물 좋아요 번호',
    post_id      bigint not null comment '게시물 번호',
    member_id    bigint not null comment '회원 번호',
    create_date  timestamp default current_timestamp comment '생성일',
    update_date  timestamp default current_timestamp on update current_timestamp comment '수정일'
) comment 'post_likes' charset = utf8mb4;

create index post_like_idx01 on post_likes (post_like_id);
create index post_like_idx02 on post_likes (post_id);
create index post_like_idx03 on post_likes (member_id);

create table post_comments
(
    post_comment_id bigint auto_increment primary key comment '게시물 댓글 번호',
    post_id         bigint not null comment '게시물 번호',
    member_id       bigint not null comment '회원 번호',
    content         text   not null comment '내용',
    like_count      int       default 0 comment '좋아요 수',
    create_date     timestamp default current_timestamp comment '생성일',
    update_date     timestamp default current_timestamp on update current_timestamp comment '수정일'
) comment 'post_comments' charset = utf8mb4;

create index post_comment_idx01 on post_comments (post_comment_id);
create index post_comment_idx02 on post_comments (post_id);
create index post_comment_idx03 on post_comments (member_id);

create table post_comment_likes
(
    post_comment_like_id bigint auto_increment primary key comment '게시물 댓글 좋아요 번호',
    post_comment_id      bigint not null comment '게시물 댓글 번호',
    member_id            bigint not null comment '회원 번호',
    create_date          timestamp default current_timestamp comment '생성일',
    update_date          timestamp default current_timestamp on update current_timestamp comment '수정일'
) comment 'post_comment_likes' charset = utf8mb4;

create index post_comment_like_idx01 on post_comment_likes (post_comment_like_id);
create index post_comment_like_idx02 on post_comment_likes (post_comment_id);
create index post_comment_like_idx03 on post_comment_likes (member_id);

create table bookmark_posts
(
    bookmark_post_id bigint auto_increment primary key comment '북마크 게시물 번호',
    post_id          bigint not null comment '게시물 번호',
    member_id        bigint not null comment '회원 번호',
    create_date      timestamp default current_timestamp comment '생성일',
    update_date      timestamp default current_timestamp on update current_timestamp comment '수정일'
) comment 'bookmark_posts' charset = utf8mb4;

create index bookmark_post_idx01 on bookmark_posts (bookmark_post_id);
create index bookmark_post_idx02 on bookmark_posts (post_id);
create index bookmark_post_idx03 on bookmark_posts (member_id);

create table follows
(
    follow_id           bigint auto_increment primary key comment '팔로우 회원 번호',
    follower_member_id  bigint not null comment '팔로워 회원 번호',
    following_member_id bigint not null comment '팔로잉 회원 번호',
    create_date         timestamp default current_timestamp comment '생성일',
    update_date         timestamp default current_timestamp on update current_timestamp comment '수정일'
) comment 'follow_members' charset = utf8mb4;

create index follow_idx01 on follows (follow_id);
create index follow_idx02 on follows (follower_member_id);
create index follow_idx03 on follows (following_member_id);