package com.example.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "DTO representing a comment on a post")
public class CommentDto {

    @Schema(description = "Unique identifier of the comment")
    private Long id;


    @Schema(description = "Name of the person who made the comment",
            requiredMode = Schema.RequiredMode.REQUIRED)
    @NotEmpty(message = "Name Should not be Empty")
    private String name;

    @Schema(description = "Email of the person who made the comment")
    private String email;

    @Schema(description = "Content/body of the comment")
    private String body;
}
