-- 회원 데이터 입력
INSERT INTO MEMBERS (EMAIL, PASSWORD, NICKNAME, NAME, PROFILE_IMAGE, ROLE)
VALUES ('test1@example.com', 'password1', 'test_nick1', 'test_name1', 'image_url1', 'ROLE_MEMBER'),
       ('test2@example.com', 'password2', 'test_nick2', 'test_name2', 'image_url2', 'ROLE_MEMBER'),
       ('test3@example.com', 'password3', 'test_nick3', 'test_name3', 'image_url3', 'ROLE_MEMBER');

-- 게시물 데이터 입력
INSERT INTO POSTS (MEMBER_ID, TITLE, CONTENT, POST_IMAGES)
VALUES (1, 'test_title1', 'test_content1', '["image1_url", "image2_url"]'),
       (2, 'test_title2', 'test_content2', '["image3_url", "image4_url"]'),
       (3, 'test_title3', 'test_content3', '["image5_url", "image6_url"]');

-- 게시물 좋아요 데이터 입력
INSERT INTO POST_LIKES (POST_ID, MEMBER_ID)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 2);

-- 게시물 댓글 데이터 입력
INSERT INTO POST_COMMENTS (POST_ID, MEMBER_ID, CONTENT)
VALUES (1, 2, 'test_comment1'),
       (1, 3, 'test_comment2'),
       (2, 1, 'test_comment3'),
       (3, 1, 'test_comment4'),
       (3, 2, 'test_comment5');

-- 게시물 댓글 좋아요 데이터 입력
INSERT INTO POST_COMMENT_LIKES (POST_COMMENT_ID, MEMBER_ID)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3);

-- 북마크 게시물 데이터 입력
INSERT INTO BOOKMARK_POSTS (POST_ID, MEMBER_ID)
VALUES (1, 1),
       (2, 2),
       (3, 3);

-- 팔로우 데이터 입력
INSERT INTO FOLLOWS (FOLLOWER_MEMBER_ID, FOLLOWING_MEMBER_ID)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 2);
