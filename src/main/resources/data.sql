INSERT INTO user1 (username, email, password, created_at, updated_at) VALUES
  ('user11', 'user11@example.com', 'password1', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('user12', 'user12@example.com', 'password2', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('user13', 'user13@example.com', 'password3', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('user14', 'user14@example.com', 'password4', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('user15', 'user15@example.com', 'password5', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO posts (content, author_id, created_at, updated_at) VALUES
  ('Post 1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Post 2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Post 3', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Post 4', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Post 5', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO notifications (content, user_id, created_at, updated_at) VALUES
  ('Notification 1', 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Notification 2', 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Notification 3', 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Notification 4', 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Notification 5', 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO user_settings (user_id, theme, notifications_enabled, created_at, updated_at) VALUES
  (1, 'theme1', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (2, 'theme2', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (3, 'theme3', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (4, 'theme4', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  (5, 'theme5', false, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO comments (content, author_id, post_id, created_at, updated_at) VALUES
  ('Comment 1', 1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Comment 2', 2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Comment 3', 3, 3, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Comment 4', 4, 4, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP),
  ('Comment 5', 5, 5, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

INSERT INTO tags (name) VALUES
  ('Tag 1'),
  ('Tag 2'),
  ('Tag 3'),
  ('Tag 4'),
  ('Tag 5');

INSERT INTO post_likes (post_id, user_id) VALUES
  (1, 1),
  (2, 2),
  (3, 3),
  (4, 4),
  (5, 5);

INSERT INTO post_shares (post_id, user_id) VALUES
  (1, 2),
  (2, 3),
  (3, 4),
  (4, 5),
  (5, 1);

INSERT INTO user_friends (user_id, friend_id) VALUES
  (1, 2),
  (1, 3),
  (2, 3),
  (2, 4),
  (3, 4);

INSERT INTO user_followers (user_id, follower_id) VALUES
  (1, 2),
  (1, 3),
  (2, 3),
  (2, 4),
  (3, 4);

 SELECT * FROM comments;
 SELECT * FROM notifications;
 SELECT * FROM user_settings;
 SELECT * FROM posts;
 SELECT * FROM tags;
 SELECT * FROM user1;

 SELECT * FROM post_likes;
 SELECT * FROM post_shares;
 SELECT * FROM user_followers;
 SELECT * FROM user_friends;
