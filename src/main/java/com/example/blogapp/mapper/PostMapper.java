package com.example.blogapp.mapper;

import com.example.blogapp.entity.Category;
import com.example.blogapp.entity.Post;
import com.example.blogapp.payload.PostDto;
import org.springframework.stereotype.Component;

import java.util.HashSet;

@Component
public class PostMapper {

    public Post toEntity(PostDto dto, Category category) {
        if (dto == null) return null;

        return Post.builder()
                .id(dto.getId())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .content(dto.getContent())
                .category(category)
                .comments(new HashSet<>())
                .build();
    }

    public PostDto mapToDTO(Post post) {
        if (post == null) return null;
        return PostDto.builder()
                .id(post.getId())
                .title(post.getTitle())
                .description(post.getDescription())
                .content(post.getContent())
                .categoryId(post.getCategory() != null ? post.getCategory().getId() : null)
                .build();
    }

}
