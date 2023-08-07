-- 회원 데이터 입력
INSERT INTO members (email, password, nickname, name, profile_image, role)
VALUES ('test1@example.com', 'password1', 'test_nick1', 'test_name1', 'image_url1', 'MEMBER'),
       ('test2@example.com', 'password2', 'test_nick2', 'test_name2', 'image_url2', 'MEMBER'),
       ('test3@example.com', 'password3', 'test_nick3', 'test_name3', 'image_url3', 'MEMBER');

-- 게시물 데이터 입력+
INSERT INTO posts (member_id, title, content, post_images)
VALUES (1, 'test_title1', 'test_content1', '["image1_url", "image2_url"]'),
       (2, 'test_title2', 'test_content2', '["image3_url", "image4_url"]'),
       (3, 'test_title3', 'test_content3', '["image5_url", "image6_url"]');

-- 게시물 좋아요 데이터 입력
INSERT INTO post_likes (post_id, member_id)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 2);

-- 게시물 댓글 데이터 입력
INSERT INTO comments (post_id, member_id, content)
VALUES (1, 2, 'test_comment1'),
       (1, 3, 'test_comment2'),
       (2, 1, 'test_comment3'),
       (3, 1, 'test_comment4'),
       (3, 2, 'test_comment5');

-- 게시물 댓글 좋아요 데이터 입력
INSERT INTO comment_likes (comment_id, member_id)
VALUES (1, 1),
       (2, 1),
       (3, 2),
       (4, 2),
       (5, 3);

-- 북마크 게시물 데이터 입력
INSERT INTO bookmarks (post_id, member_id)
VALUES (1, 1),
       (2, 2),
       (3, 3);

-- 팔로우 데이터 입력
INSERT INTO follows (follower_member_id, following_member_id)
VALUES (1, 2),
       (1, 3),
       (2, 1),
       (3, 1),
       (3, 2);
