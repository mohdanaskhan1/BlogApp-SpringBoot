package com.example.blogapp.controller;
import com.example.blogapp.payload.CommentDto;
import com.example.blogapp.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentsResource {

    private CommentService commentService;

    public CommentsResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@PathVariable("postId") Long postId, @RequestBody CommentDto commentDto){
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(@PathVariable("postId") Long postId){
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable("postId") Long postId,  @PathVariable("id") Long commentId){
        return ResponseEntity.ok(commentService.getCommentsById(postId,commentId));
    }

    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentById(@PathVariable("postId") Long postId,
                                                        @PathVariable("id") Long commentId,
                                                        @RequestBody CommentDto commentDto){
        return ResponseEntity.ok(commentService.updateCommentById(postId,commentId,commentDto));

    }

    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(@PathVariable("postId") Long postId,  @PathVariable("id") Long commentId){
        commentService.deleteCommentsById(postId,commentId);
        return new ResponseEntity<>("Comment Deleted Successfully",HttpStatus.OK);
    }





}
