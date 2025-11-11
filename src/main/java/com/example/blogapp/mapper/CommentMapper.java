package com.example.blogapp.mapper;

import com.example.blogapp.entity.Comment;
import com.example.blogapp.payload.CommentDto;
import org.springframework.stereotype.Component;

@Component
public class CommentMapper {
    public Comment mapToComment(CommentDto commentDto) {
        return Comment.builder()
                .id(commentDto.getId())
                .name(commentDto.getName())
                .email(commentDto.getEmail())
                .body(commentDto.getBody())
                .build();
    }

    public CommentDto mapToCommentDto(Comment comment) {
        return CommentDto.builder()
                .id(comment.getId())
                .name(comment.getName())
                .email(comment.getEmail())
                .body(comment.getBody())
                .build();
    }
}
