package com.example.demo.components.post;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.dto.PostDto;
import com.example.demo.model.Comment;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    // 對文章按讚
    @PostMapping("/{postId}/like")
    public ResponseEntity<String> likePost(
            @PathVariable("postId") int postId,
            @RequestParam("userId") int userId) {
        postService.likePost(postId, userId);
        return ResponseEntity.ok("Post liked successfully");
    }

    // 編輯評論
    @PostMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<Comment> editComment(
            @PathVariable("commentId") int commentId,
            @RequestBody String comment) {
        Comment updatedComment = postService.editComment(commentId, comment);
        return ResponseEntity.ok(updatedComment);
    }

    // 新增評論
    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> addComment(
            @PathVariable("postId") int postId,
            @RequestParam("userId") int userId,
            @RequestBody String comment) {
        postService.addComment(postId, userId, comment);
        return ResponseEntity.ok("Comment added successfully");
    }

    // 刪除評論
    @DeleteMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteComment(
            @PathVariable("postId") int postId,
            @PathVariable("commentId") int commentId,
            @RequestParam("userId") int userId) {
        postService.deleteComment(postId, commentId, userId);
        return ResponseEntity.ok("Comment deleted successfully");
    }

    // 取得文章資料
    @GetMapping("/{postId}")
    public ResponseEntity<PostDto> getPostById(@PathVariable int postId) {
        PostDto postDto = postService.getPostById(postId);
        if (postDto != null) {
            return ResponseEntity.ok(postDto);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 取得全部文章
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts() {
        List<PostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 新增文章資料
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        PostDto createdPost = postService.createPost(postDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdPost);
    }

    // 更新文章資料
    @PutMapping("/{postId}")
    public ResponseEntity<PostDto> updatePost(
            @PathVariable int postId,
            @RequestBody PostDto postDto) {
        PostDto updatedPost = postService.updatePost(postId, postDto);
        return ResponseEntity.ok(updatedPost);
    }

    // 刪除文章資料
    @DeleteMapping("/{postId}")
    public ResponseEntity<Void> deletePost(@PathVariable int postId) {
        postService.deletePost(postId);
        return ResponseEntity.noContent().build();
    }
}
