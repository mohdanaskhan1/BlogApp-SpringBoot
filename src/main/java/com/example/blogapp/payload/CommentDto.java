package com.example.blogapp.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class CommentDto {
    private Long id;

    @NotEmpty(message = "Name Should not be Empty")
    private String name;
    private String email;
    private String body;
}
