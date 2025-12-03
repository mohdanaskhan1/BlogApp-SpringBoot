package com.example.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "DTO representing a blog post")
public class PostDto {
    @Schema(description = "Unique identifier of the post")
    private Long id;

    @Schema(description = "Title of the post",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Post Title Should not be Empty")
    @Size(min = 2, message = "Post Title should have at least 2 Characters")
    private String title;

    @Schema(description = "Short description of the post",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Post Description should not be Empty")
    @Size(min = 10, message = "Post Description should have at least 10 Character")
    private String description;


    @Schema(description = "Detailed content of the post",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Post Content should not be Empty")
    private String content;

    @Schema(description = "List of comments associated with the post")
    private Set<CommentDto> comments;

}
