package com.example.demo.post;

import com.example.demo.MapToDto;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Post;
import com.example.demo.model.User;
import com.example.demo.user.dao.UserDao;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostService {

    private final PostDao postDao;
    private final UserDao userDao;
    private final MapToDto mapToDto;

    public PostService(
            PostDao postDao,
            UserDao userDao,
            MapToDto mapToDto) {
        this.postDao = postDao;
        this.userDao = userDao;
        this.mapToDto = mapToDto;
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
}
