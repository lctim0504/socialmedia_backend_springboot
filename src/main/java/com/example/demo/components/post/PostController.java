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

import com.example.demo.dto.CommentDto;
import com.example.demo.dto.PostDto;
import com.example.demo.dto.ReplyDto;
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
    @PutMapping("/{postId}/comments/{commentId}")
    public ResponseEntity<String> editComment(
            @PathVariable("commentId") int commentId,
            @RequestBody ReplyDto comment) {
        postService.editComment(commentId, comment.getComment());
        return ResponseEntity.ok("Post updated successfully");
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentsbyPostId(
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "likes") String sort,
            @PathVariable("postId") Integer postId) {
        List<CommentDto> comments;
        comments = postService.getCommentsByPostId(postId, sort, limit, offset);
        return ResponseEntity.ok(comments);
    }

    // 新增評論
    @PostMapping("/{postId}/comments")
    public ResponseEntity<String> addComment(
            @PathVariable("postId") int postId,
            @RequestParam("userId") int userId,
            @RequestBody ReplyDto comment) {
        postService.addComment(postId, userId, comment.getComment());
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

    // 取得作者文章資料
    // 取得使用者好友文章資料
    // 取得全部文章
    @GetMapping
    public ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(required = false, defaultValue = "10") int limit,
            @RequestParam(required = false, defaultValue = "0") int offset,
            @RequestParam(required = false, defaultValue = "updatedAt") String sort,
            @RequestParam(required = false) Integer authorId) {
        List<PostDto> posts;
        if (authorId != null) {
            posts = postService.getAuthorPostsSortedBy(authorId, sort, limit, offset);
        } else {
            posts = postService.getAllPostsSortedBy(sort, limit, offset);

        }
        // if (authorId != null) {
        // if (sort != null && (sort.equals("createdAt") || sort.equals("commentQty")))
        // {
        // posts = postService.getAuthorPostsSortedBy(authorId, sort, limit, offset);
        // } else {
        // posts = postService.getAuthorPosts(authorId, limit, offset);
        // }
        // } else {
        // if (sort != null && (sort.equals("createdAt") || sort.equals("commentQty")))
        // {
        // posts = postService.getAllPostsSortedBy(sort, limit, offset);
        // } else {
        // posts = postService.getAllPosts(limit, offset);
        // }
        // }
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
