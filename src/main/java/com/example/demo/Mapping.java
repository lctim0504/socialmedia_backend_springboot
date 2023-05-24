package com.example.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.TagDto;
import com.example.demo.dto.UserDto;
import com.example.demo.model.Comment;
import com.example.demo.model.Post;
import com.example.demo.model.Tag;
import com.example.demo.model.User;

@Service
public class Mapping {

    public TagDto TagToDto(Tag tag) {
        TagDto tagDto = new TagDto();
        tagDto.setId(tag.getId());
        tagDto.setName(tag.getName());
        return tagDto;
    }

    public List<TagDto> TagsToDto(List<Tag> tags) {
        return tags.stream().map(this::TagToDto).collect(Collectors.toList());
    }

    public CommentDto CommentToDto(Comment comment) {
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comment.getId());
        commentDto.setAuthor(UserToDto(comment.getAuthor()));
        commentDto.setContent(comment.getContent());
        return commentDto;
    }

    public User DtoToUser(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        return user;
    }

    public UserDto UserToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUsername(user.getUsername());
        return userDto;
    }

    public List<UserDto> UsersToDto(List<User> users) {
        return users.stream().map(this::UserToDto).collect(Collectors.toList());
    }

    public Post DtoToPost(PostDto postDto) {
        Post post = new Post();
        post.setContent(postDto.getContent());
        post.setAuthorId(postDto.getAuthorId());
        return post;
    }

    public PostDto PostToDto(Post post) {
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setContent(post.getContent());
        postDto.setCreatedAt(post.getCreatedAt());
        postDto.setUpdatedAt(post.getUpdatedAt());
        postDto.setAuthorId(post.getAuthorId());
        postDto.setAuthor(UserToDto(post.getAuthor()));
        postDto.setCommentQty(post.getCommentQty());
        postDto.setComment(CommentToDto(post.getComment()));
        postDto.setLikes(UsersToDto(post.getLikes()));
        postDto.setShares(UsersToDto(post.getShares()));
        postDto.setTags(TagsToDto(post.getTags()));
        return postDto;
    }

    public List<PostDto> PostsToDto(List<Post> posts) {
        return posts.stream().map(this::PostToDto).collect(Collectors.toList());
    }
}
