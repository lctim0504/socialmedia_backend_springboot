package com.example.demo.components.comment;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.example.demo.Mapping;
import com.example.demo.components.comment.dao.CommentLikeDao;
import com.example.demo.components.post.dao.CommentDao;
import com.example.demo.components.user.UserDao;
import com.example.demo.dto.CommentDto;
import com.example.demo.model.Comment;
import com.example.demo.model.CommentLikes;
import com.example.demo.model.User;

@Service
public class CommentService {

    private final CommentDao commentDao;
    private final CommentLikeDao commentLikeDao;
    private final UserDao userDao;
    private final Mapping mapping;

    public CommentService(
            CommentDao commentDao,
            CommentLikeDao commentLikeDao,
            UserDao userDao,
            Mapping mapping) {
        this.commentDao = commentDao;
        this.commentLikeDao = commentLikeDao;
        this.userDao = userDao;
        this.mapping = mapping;
    }

    public void likeComment(int commentId, int userId) {
        Optional<Comment> optionalComment = commentDao.findById(commentId);
        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalComment.isPresent() && optionalUser.isPresent()) {
            Optional<CommentLikes> isLiked = commentLikeDao.findByCommentIdAndUserId(commentId, userId);
            Comment comment = optionalComment.get();
            User user = optionalUser.get();
            if (!isLiked.isPresent()) {
                CommentLikes commentLike = new CommentLikes();
                commentLike.setComment(comment);
                commentLike.setUser(user);
                commentLikeDao.save(commentLike);
            } else {
                CommentLikes commentLike = isLiked.get();
                commentLikeDao.delete(commentLike);
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment or User not found");
        }
    }

    // 取得文章全部評論
    public List<CommentDto> getCommentByParentId(int parentId) {
        Optional<Comment> optionalComment = commentDao.findById(parentId);
        if (optionalComment.isPresent()) {
            List<Comment> comments = commentDao.findByParentId(parentId);
            return mapping.CommentsToDto(comments);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment not found");
        }
    }

    // 新增評論回覆
    public void addComment(int parentId, int userId, String comment1) {
        Optional<Comment> optionalComment = commentDao.findById(parentId);
        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalUser.isPresent() && optionalComment.isPresent()) {
            User user = optionalUser.get();
            Comment parentComment = optionalComment.get();
            Comment comment = new Comment();
            comment.setAuthor(user);
            comment.setParent(parentComment);
            comment.setContent(comment1);
            comment.setCreatedAt(LocalDateTime.now());
            comment.setUpdatedAt(LocalDateTime.now());
            commentDao.save(comment);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ParentComment or User not found");
        }
    }
}
