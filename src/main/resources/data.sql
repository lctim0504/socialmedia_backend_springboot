-- 更新 user1 表
INSERT INTO user1 (username, email, password, created_at, updated_at) VALUES
  ('user11', 'user11@example.com', 'password1', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user12', 'user12@example.com', 'password2', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user13', 'user13@example.com', 'password3', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user14', 'user14@example.com', 'password4', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user15', 'user15@example.com', 'password5', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user16', 'user16@example.com', 'password6', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user17', 'user17@example.com', 'password7', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user18', 'user18@example.com', 'password8', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user19', 'user19@example.com', 'password9', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('user20', 'user20@example.com', 'password10', (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()));

-- 更新 posts 表
INSERT INTO posts (content, author_id, created_at, updated_at) VALUES
  ('Post 1', 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 2', 2, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 3', 3, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 4', 4, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 5', 5, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 6', 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 7', 2, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 8', 3, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 9', 4, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Post 10', 5, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()));

-- 更新 notifications 表
INSERT INTO notifications (content, user_id, created_at, updated_at) VALUES
  ('Notification 1', 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 2', 2, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 3', 3, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 4', 4, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 5', 5, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 6', 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 7', 2, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 8', 3, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 9', 4, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Notification 10', 5, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()));

-- 更新 user_settings 表
INSERT INTO user_settings (user_id, theme, notifications_enabled, created_at, updated_at) VALUES
  (1, 'theme1', true, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (2, 'theme2', true, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (3, 'theme3', false, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (4, 'theme4', true, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (5, 'theme5', false, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (6, 'theme6', true, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (7, 'theme7', false, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (8, 'theme8', true, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (9, 'theme9', false, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  (10, 'theme10', true, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()));

-- 更新 comments 表
INSERT INTO comments (content, author_id, post_id, parent_id, created_at, updated_at) VALUES
  ('Comment 1', 1, 1, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 2', 2, null, 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 3', 3, null, 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 4', 4, 2, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 5', 5, 5, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 6', 1, 1, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 7', 2, 2, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 8', 3, 3, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 9', 4, 4, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Comment 10', 5, 5, null, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()));

-- 更新 comment_likes 表
INSERT INTO comment_likes (comment_id, user_id) VALUES
  (1, 1),
  (1, 2),
  (2, 3),
  (3, 4),
  (4, 5),
  (5, 1),
  (6, 2),
  (7, 3),
  (8, 4),
  (9, 5),
  (10, 1);

-- 更新 comment_replies 表
INSERT INTO comment_replies (content, author_id, comment_id, created_at, updated_at) VALUES
  ('Reply 1 to Comment 1', 1, 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 2 to Comment 1', 2, 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 1 to Comment 2', 3, 2, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 1 to Comment 3', 4, 3, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 1 to Comment 4', 5, 4, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 1 to Comment 1', 1, 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 2 to Comment 1', 2, 1, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 1 to Comment 2', 3, 2, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 1 to Comment 3', 4, 3, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random())),
  ('Reply 1 to Comment 4', 5, 4, (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()), (CURRENT_TIMESTAMP - INTERVAL '14 days' * random()));

-- 更新 tags 表
INSERT INTO tags (name) VALUES
  ('Tag 1'),
  ('Tag 2'),
  ('Tag 3'),
  ('Tag 4'),
  ('Tag 5'),
  ('Tag 6'),
  ('Tag 7'),
  ('Tag 8'),
  ('Tag 9'),
  ('Tag 10');

-- 更新 post_tags 表
INSERT INTO post_tags (tag_id, post_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (6, 1),
  (7, 2),
  (8, 3),
  (9, 4),
  (10, 5);

-- 更新 post_likes 表
INSERT INTO post_likes (post_id, user_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5),
  (6, 1),
  (7, 2),
  (8, 3),
  (9, 4),
  (10, 5);

-- 更新 post_shares 表
INSERT INTO post_shares (post_id, user_id) VALUES
  (1, 2),
  (2, 3),
  (3, 4),
  (4, 5),
  (5, 1),
  (6, 2),
  (7, 3),
  (8, 4),
  (9, 5),
  (10, 1);

-- 更新 user_friends 表
INSERT INTO user_friends (user_id, friend_id, status) VALUES
  (1, 2, 'ACCEPTED'),
  (1, 3, 'PENDING'),
  (1, 4, 'REJECTED'),
  (5, 1, 'ACCEPTED'),
  (6, 1, 'PENDING'),
  (7, 1, 'REJECTED'),
  (2, 4, 'REJECTED'),
  (3, 4, 'PENDING'),
  (4, 5, 'ACCEPTED'),
  (6, 2, 'PENDING'),
  (7, 3, 'ACCEPTED'),
  (8, 4, 'REJECTED');

-- 更新 user_followers 表
INSERT INTO user_followers (user_id, follower_id) VALUES
  (1, 2),
  (1, 3),
  (2, 3),
  (2, 4),
  (3, 4),
  (4, 5),
  (5, 1),
  (6, 2),
  (7, 3),
  (8, 4);


 SELECT * FROM tags;
 SELECT * FROM posts;
 SELECT * FROM user1;
 SELECT * FROM comments;
 SELECT * FROM post_likes;
 SELECT * FROM post_shares;
 SELECT * FROM user_friends;
 SELECT * FROM user_settings;
 SELECT * FROM notifications;
 SELECT * FROM comment_likes;
 SELECT * FROM user_followers;
 SELECT * FROM comment_replies;
