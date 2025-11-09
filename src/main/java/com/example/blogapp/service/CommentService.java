package com.example.blogapp.service;

import com.example.blogapp.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(Long postId, CommentDto commentDto);

    List<CommentDto> getCommentsByPostId(Long postId);

    CommentDto getCommentsById(Long postId, Long commentId);

    void deleteCommentsById(Long postId, Long commentId);

    CommentDto updateCommentById(Long postId, Long commentId, CommentDto commentDto);
}
