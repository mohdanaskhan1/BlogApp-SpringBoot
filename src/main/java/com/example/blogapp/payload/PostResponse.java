package com.example.blogapp.payload;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Schema(description = "Paginated response for blog posts")
public class PostResponse {
    @Schema(description = "List of posts for the current page")
    private List<PostDto> content;

    @Schema(description = "Current page number (zero-based)", example = "0")
    private int pageNo;

    @Schema(description = "Number of posts per page", example = "10")
    private int pageSize;

    @Schema(description = "Total number of posts available", example = "57")
    private long totalElements;

    @Schema(description = "Total number of pages available", example = "6")
    private int totalPages;

    @Schema(description = "Flag indicating if this is the last page", example = "false")
    private boolean last;
}
