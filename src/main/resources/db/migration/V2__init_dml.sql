-- members 테이블에 테스트 데이터 삽입
INSERT INTO members (email, password, nickname, name, profile_image, follower_count, following_count, post_count, role)
VALUES
    ('test1@email.com', SHA2('password1', 256), 'nickname1', 'name1', 'profile_image_url_1', 10, 5, 3, 'ROLE_MEMBER'),
    ('test2@email.com', SHA2('password2', 256), 'nickname2', 'name2', 'profile_image_url_2', 20, 10, 4, 'ROLE_MEMBER');

-- posts 테이블에 테스트 데이터 삽입
INSERT INTO posts (member_id, title, content, post_images, like_count)
VALUES
    (1, 'title1', 'content1', '["image1_url_1", "image1_url_2"]', 5),
    (2, 'title2', 'content2', '["image2_url_1"]', 3);

-- post_likes 테이블에 테스트 데이터 삽입
INSERT INTO post_likes (post_id, member_id)
VALUES
    (1, 2),
    (2, 1);

-- post_comments 테이블에 테스트 데이터 삽입
INSERT INTO post_comments (post_id, member_id, content, like_count)
VALUES
    (1, 2, 'comment1', 2),
    (2, 1, 'comment2', 1);

-- post_comment_likes 테이블에 테스트 데이터 삽입
INSERT INTO post_comment_likes (post_comment_id, member_id)
VALUES
    (1, 1),
    (2, 2);

-- bookmark_posts 테이블에 테스트 데이터 삽입
INSERT INTO bookmark_posts (post_id, member_id)
VALUES
    (1, 1),
    (2, 2);

-- follows 테이블에 테스트 데이터 삽입
INSERT INTO follows (follower_member_id, following_member_id)
VALUES
    (1, 2),
    (2, 1);
