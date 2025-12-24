package com.example.blogapp.controller;

import com.example.blogapp.payload.CommentDto;
import com.example.blogapp.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
@Tag(
        name = "Comment Resource APIs",
        description = "CRUD REST APIs for managing comments belonging to blog posts"
)
public class CommentsResource {

    private final CommentService commentService;

    public CommentsResource(CommentService commentService) {
        this.commentService = commentService;
    }

    @Operation(
            summary = "Create Comment on a Post",
            description = "Creates a new comment for the given post ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Comment created successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(
            @Parameter(description = "ID of the post to comment on", example = "1")
            @PathVariable("postId") Long postId,

            @RequestBody CommentDto commentDto) {
        return new ResponseEntity<>(commentService.createComment(postId, commentDto), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get All Comments for a Post",
            description = "Retrieve all comments that belong to a specific post ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comments retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getCommentByPostId(
            @Parameter(description = "ID of the post", example = "1")
            @PathVariable("postId") Long postId) {
        return ResponseEntity.ok(commentService.getCommentsByPostId(postId));
    }


    @Operation(
            summary = "Get Comment by ID",
            description = "Retrieve a single comment by comment ID for a specific post"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment retrieved successfully"),
            @ApiResponse(responseCode = "404", description = "Post or Comment not found")
    })
    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> getCommentById(
            @Parameter(description = "ID of the post", example = "1")
            @PathVariable("postId") Long postId,

            @Parameter(description = "ID of the comment", example = "5")
            @PathVariable("id") Long commentId) {
        return ResponseEntity.ok(commentService.getCommentsById(postId, commentId));
    }



    @Operation(
            summary = "Update Comment",
            description = "Update a comment belonging to a specific post"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment updated successfully"),
            @ApiResponse(responseCode = "404", description = "Post or Comment not found")
    })
    @PutMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<CommentDto> updateCommentById(
            @Parameter(description = "ID of the post", example = "1")
            @PathVariable("postId") Long postId,

            @Parameter(description = "ID of the comment", example = "3")
            @PathVariable("id") Long commentId,

            @RequestBody CommentDto commentDto) {
        return ResponseEntity.ok(commentService.updateCommentById(postId, commentId, commentDto));

    }


    @Operation(
            summary = "Delete Comment",
            description = "Deletes a comment belonging to a specific post"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Comment deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post or Comment not found")
    })
    @DeleteMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<String> deleteCommentById(
            @Parameter(description = "ID of the post", example = "1")
            @PathVariable("postId") Long postId,

            @Parameter(description = "ID of the comment", example = "3")
            @PathVariable("id") Long commentId) {
        commentService.deleteCommentsById(postId, commentId);
        return new ResponseEntity<>("Comment Deleted Successfully", HttpStatus.OK);
    }


}
