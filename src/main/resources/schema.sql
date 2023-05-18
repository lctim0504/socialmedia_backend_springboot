-- 刪除舊的 users 資料表
DROP TABLE IF EXISTS users CASCADE;

DROP TABLE IF EXISTS posts CASCADE;

DROP TABLE IF EXISTS notifications CASCADE;

DROP TABLE IF EXISTS user_settings CASCADE;

DROP TABLE IF EXISTS comments CASCADE;

DROP TABLE IF EXISTS tags CASCADE;

DROP TABLE IF EXISTS PostLikes CASCADE;

DROP TABLE IF EXISTS PostShares CASCADE;

DROP TABLE IF EXISTS UserFriends CASCADE;

DROP TABLE IF EXISTS UserFollowers CASCADE;

-- 建立 user1 表
CREATE TABLE IF NOT EXISTS user1 (
    id SERIAL PRIMARY KEY,
    username VARCHAR(255) UNIQUE,
    email VARCHAR(255) UNIQUE,
    password VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- 建立 posts 表
CREATE TABLE IF NOT EXISTS posts (
    id SERIAL PRIMARY KEY,
    content TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    author_id INT,
    FOREIGN KEY (author_id) REFERENCES user1 (id)
);

-- 建立 notifications 表
CREATE TABLE IF NOT EXISTS notifications (
    id SERIAL PRIMARY KEY,
    content TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    user_id INT,
    FOREIGN KEY (user_id) REFERENCES user1 (id)
);

-- 建立 user_settings 表
CREATE TABLE IF NOT EXISTS user_settings (
    id SERIAL PRIMARY KEY,
    user_id INT UNIQUE,
    theme VARCHAR(255),
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    notifications_enabled BOOLEAN DEFAULT true,
    FOREIGN KEY (user_id) REFERENCES user1 (id)
);

-- 建立 comments 表
CREATE TABLE IF NOT EXISTS comments (
    id SERIAL PRIMARY KEY,
    content TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    author_id INT,
    post_id INT,
    FOREIGN KEY (author_id) REFERENCES user1 (id),
    FOREIGN KEY (post_id) REFERENCES posts (id)
);

-- 建立 tags 表
CREATE TABLE IF NOT EXISTS tags (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) UNIQUE
);

-- 建立 PostLikes 表
CREATE TABLE IF NOT EXISTS post_likes (
    post_id INT,
    user_id INT,
    PRIMARY KEY (post_id, user_id),
    FOREIGN KEY (post_id) REFERENCES posts (id),
    FOREIGN KEY (user_id) REFERENCES user1 (id)
);

-- 建立 PostShares 表
CREATE TABLE IF NOT EXISTS post_shares (
    post_id INT,
    user_id INT,
    PRIMARY KEY (post_id, user_id),
    FOREIGN KEY (post_id) REFERENCES posts (id),
    FOREIGN KEY (user_id) REFERENCES user1 (id)
);

-- 建立 UserFriends 表
CREATE TABLE IF NOT EXISTS user_friends (
    user_id INT,
    friend_id INT,
    status VARCHAR(20),
    PRIMARY KEY (user_id, friend_id),
    FOREIGN KEY (user_id) REFERENCES user1 (id),
    FOREIGN KEY (friend_id) REFERENCES user1 (id)
);


-- 建立 UserFollowers 表
CREATE TABLE IF NOT EXISTS user_followers (
    user_id INT,
    follower_id INT,
    PRIMARY KEY (user_id, follower_id),
    FOREIGN KEY (user_id) REFERENCES user1 (id),
    FOREIGN KEY (follower_id) REFERENCES user1 (id)
);

