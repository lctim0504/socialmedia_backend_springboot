package com.example.demo.components.post;

import com.example.demo.Mapping;
import com.example.demo.components.post.dao.CommentDao;
import com.example.demo.components.post.dao.PostDao;
import com.example.demo.components.post.dao.PostLikeDao;
import com.example.demo.components.user.UserDao;
import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.PostLikes;
import com.example.demo.model.User;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostDao postDao;
    private final PostLikeDao postLikeDao;
    private final UserDao userDao;
    private final CommentDao commentDao;
    private final Mapping mapping;

    public PostService(
            PostDao postDao,
            PostLikeDao postLikeDao,
            UserDao userDao,
            Mapping mapping,
            CommentDao commentDao) {
        this.postDao = postDao;
        this.postLikeDao = postLikeDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
        this.mapping = mapping;
    }

    public PostDto getPostById(int postId) {
        Optional<Post> optionalPost = postDao.findById(postId);
        if (optionalPost.isPresent()) {
            return mapping.PostToDto(optionalPost.get());
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with ID: " + postId);
    }

    // 取得全部文章資料
    // public List<PostDto> getAllPosts() {
    // List<Post> posts = postDao.findAll();
    // return mapping.PostsToDto(posts);
    // }
    public List<PostDto> getAllPosts(int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Post> postPage = postDao.findAll(pageable);
        return mapping.PostsToDto(postPage.getContent());
    }

    public List<PostDto> getAllPostsSortedBy(String sort, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(sort).descending());
        Page<Post> postPage = postDao.findAll(pageable);
        return mapping.PostsToDto(postPage.getContent());
    }

    public List<PostDto> getAuthorPostsSortedBy(int authorId, String sort, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit, Sort.by(sort).descending());
        Page<Post> postPage = postDao.findByAuthorId(authorId, pageable);
        return mapping.PostsToDto(postPage.getContent());
    }

    public List<PostDto> getAuthorPosts(int authorId, int limit, int offset) {
        Pageable pageable = PageRequest.of(offset, limit);
        Page<Post> postPage = postDao.findByAuthorId(authorId, pageable);
        return mapping.PostsToDto(postPage.getContent());
    }

    // 新增文章資料
    public PostDto createPost(PostDto postDto) {
        Post post = mapping.DtoToPost(postDto);
        post.setCreatedAt(LocalDateTime.now());
        post.setUpdatedAt(LocalDateTime.now());
        Post createdPost = postDao.save(post);
        Optional<User> optionalUser = userDao.findById(postDto.getAuthorId());
        createdPost.setAuthor(optionalUser.get());
        return mapping.PostToDto(createdPost);
    }

    // 更新文章資料
    public PostDto updatePost(int postId, PostDto postDto) {
        Optional<Post> optionalPost = postDao.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setContent(postDto.getContent());
            Post updatedPost = postDao.save(post);
            return mapping.PostToDto(updatedPost);
        }
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with ID: " + postId);
    }

    // 刪除文章資料
    public void deletePost(int postId) {
        Optional<Post> optionalPost = postDao.findById(postId);
        if (optionalPost.isPresent()) {
            postDao.delete(optionalPost.get());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found with ID: " + postId);
        }
    }

    // 對文章按讚
    public void likePost(int postId, int userId) {
        Optional<Post> optionalPost = postDao.findById(postId);
        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalUser.isPresent() && optionalPost.isPresent()) {
            Optional<PostLikes> isLiked = postLikeDao.findByUserIdAndPostId(userId, postId);
            User user = optionalUser.get();
            Post post = optionalPost.get();
            if (!isLiked.isPresent()) {
                PostLikes postLike = new PostLikes();
                postLike.setUser(user);
                postLike.setPost(post);
                postLikeDao.save(postLike);
            } else {
                PostLikes postLike = isLiked.get();
                postLikeDao.delete(postLike);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post or User not found");
        }
    }

    // 取得文章全部評論
    public List<CommentDto> getCommentsByPostId(int postId, String sort, int limit, int offset) {

        Optional<Post> optionalPost = postDao.findById(postId);
        if (optionalPost.isPresent()) {
            Pageable pageable = PageRequest.of(offset, limit, Sort.by(sort).descending());
            Page<Comment> comments = commentDao.findByPostId(postId, pageable);
            return mapping.CommentsToDto(comments.getContent());
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post not found");
        }
    }

    // 新增評論
    public void addComment(int postId, int userId, String comment1) {
        Optional<Post> optionalPost = postDao.findById(postId);
        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalUser.isPresent() && optionalPost.isPresent()) {
            User user = optionalUser.get();
            Post post = optionalPost.get();
            Comment comment = new Comment();
            comment.setAuthor(user);
            comment.setPost(post);
            comment.setContent(comment1);
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUpdatedAt(LocalDateTime.now());
            commentDao.save(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post or User not found");
        }
    }

    // 刪除評論
    public void deleteComment(int postId, int commentId, int userId) {
        Optional<Post> optionalPost = postDao.findById(postId);
        Optional<User> optionalUser = userDao.findById(userId);
        Optional<Comment> optionalcomment = commentDao.findById(commentId);
        if (optionalUser.isPresent() && optionalPost.isPresent() && optionalcomment.isPresent()) {
            Comment comment = optionalcomment.get();
            commentDao.delete(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Post, User, or Comment not found");
        }
    }

    // 編輯評論
    public void editComment(int commentId, String comment1) {
        Optional<Comment> optionalcomment = commentDao.findById(commentId);
        if (optionalcomment.isPresent()) {
            Comment comment = optionalcomment.get();
            comment.setContent(comment1);
            comment.setUpdatedAt(LocalDateTime.now());
            commentDao.save(comment);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found with ID: " + commentId);
    }
}
