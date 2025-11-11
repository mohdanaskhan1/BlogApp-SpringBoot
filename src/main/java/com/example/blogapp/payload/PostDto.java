package com.example.blogapp.payload;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class PostDto {
    private Long id;

    @NotEmpty(message = "Post Title Should not be Empty")
    @Size(min = 2, message = "Post Title should have at least 2 Characters")
    private String title;

    @NotEmpty(message = "Post Description should not be Empty")
    @Size(min=10, message = "Post Description should have at least 10 Character")
    private String description;

    @NotEmpty(message = "Post Content should not be Empty")
    private String content;
    private Set<CommentDto> comments;

}
