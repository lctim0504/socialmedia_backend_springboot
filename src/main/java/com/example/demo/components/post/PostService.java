package com.example.demo.components.post;

import com.example.demo.components.post.dao.CommentDao;
import com.example.demo.components.post.dao.PostDao;
import com.example.demo.components.post.dao.PostLikeDao;
import com.example.demo.components.post.dao.PostShareDao;
import com.example.demo.components.user.UserDao;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.PostLikes;
import com.example.demo.model.User;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class PostService {

    private final PostDao postDao;
    private final PostLikeDao postLikeDao;
    private final PostShareDao postShareDao;
    private final UserDao userDao;
    private final CommentDao commentDao;

    public PostService(
            PostDao postDao,
            PostLikeDao postLikeDao,
            PostShareDao postShareDao,
            UserDao userDao,
            CommentDao commentDao) {
        this.postDao = postDao;
        this.postLikeDao = postLikeDao;
        this.postShareDao = postShareDao;
        this.userDao = userDao;
        this.commentDao = commentDao;
    }

    public PostDto getPostById(int postId) {
        Optional<Post> optionalPost = postDao.findById(postId);
        return optionalPost.map(this::mapPostToDto).orElse(null);
    }

    public List<PostDto> getAllPosts() {
        List<Post> posts = postDao.findAll();
        return mapPostsToDto(posts);
    }

    public PostDto createPost(PostDto postDto) {
        Post post = mapDtoToPost(postDto);
        Post createdPost = postDao.save(post);
        return mapPostToDto(createdPost);
    }

    public PostDto updatePost(int postId, PostDto postDto) {
        Optional<Post> optionalPost = postDao.findById(postId);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setContent(postDto.getContent());
            Post updatedPost = postDao.save(post);
            return mapPostToDto(updatedPost);
        }
        return null;
    }

    public boolean deletePost(int postId) {
        Optional<Post> optionalPost = postDao.findById(postId);
        if (optionalPost.isPresent()) {
            postDao.delete(optionalPost.get());
            return true;
        }
        return false;
    }

    private PostDto mapPostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setUpdatedAt(post.getUpdatedAt());
        postDto.setAuthorId(post.getAuthorId());
        return postDto;
    }

    private List<PostDto> mapPostsToDto(List<Post> posts) {
        return posts.stream().map(this::mapPostToDto).collect(Collectors.toList());
    }

    private Post mapDtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setAuthorId(postDto.getAuthorId());
        return post;
    }

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
        }
    }

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
        }
    }

    public void deleteComment(int postId, int commentId, int userId) {
        Optional<Post> optionalPost = postDao.findById(postId);
        Optional<User> optionalUser = userDao.findById(userId);
        Optional<Comment> optionalcomment = commentDao.findById(commentId);
        if (optionalUser.isPresent() && optionalPost.isPresent() && optionalcomment.isPresent()) {
            Comment comment = optionalcomment.get();
            commentDao.delete(comment);
        }
    }

    public Comment editComment(int commentId, String comment1) {
        Optional<Comment> optionalcomment = commentDao.findById(commentId);
        if (optionalcomment.isPresent()) {
            Comment comment = optionalcomment.get();
            comment.setContent(comment1);
            comment.setUpdatedAt(LocalDateTime.now());
            return commentDao.save(comment);
        }
        return null;
    }
    
}
