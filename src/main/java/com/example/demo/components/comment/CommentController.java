package com.example.demo.components.comment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import com.example.demo.dto.CommentDto;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }
    @PostMapping("/{commentId}/like")
    public ResponseEntity<Void> likeComment(
            @PathVariable("commentId") int commentId,
            @RequestParam("userId") int userId) {
        commentService.likeComment(commentId, userId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{commentId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByParentId(@PathVariable("commentId") int commentId) {
        List<CommentDto> comments = commentService.getCommentByParentId(commentId);
        return ResponseEntity.ok(comments);
    }

    // 新增評論
    @PostMapping("/{commentId}/comments")
    public ResponseEntity<String> addComment(
            @PathVariable("commentId") int parentId,
            @RequestParam("userId") int userId,
            @RequestBody String comment) {
        commentService.addComment(parentId, userId, comment);
        return ResponseEntity.ok("Comment added successfully");
    }
}
