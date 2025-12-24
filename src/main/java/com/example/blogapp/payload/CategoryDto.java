package com.example.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "DTO representing a blog category")
public class CategoryDto {
    private Long id;
    private String name;
    private String description;
}
