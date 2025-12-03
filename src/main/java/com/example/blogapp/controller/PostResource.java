package com.example.blogapp.controller;

import com.example.blogapp.payload.PostDto;
import com.example.blogapp.payload.PostResponse;
import com.example.blogapp.service.PostService;
import com.example.blogapp.utils.AppConstants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/posts")
@Tag(
        name = "Post Resource APIs",
        description = "CRUD REST APIs for creating, retrieving, updating and deleting blog posts"
)
public class PostResource {

    private final PostService postService;

    public PostResource(PostService postService) {
        this.postService = postService;
    }


    @Operation(
            summary = "Create New Post",
            description = "Create a new blog post and return the created post object",
            tags = {"Post Resource APIs"}
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Post created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data")
    })
    @PostMapping
    public ResponseEntity<PostDto> createPost(@Valid @RequestBody PostDto postDto) {
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get All Posts",
            description = "Retrieve all posts with pagination and sorting"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Posts fetched successfully")
    })
    @GetMapping
    public ResponseEntity<PostResponse> getAllPosts(
            @Parameter(description = "Page number for pagination", example = "0")
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,

            @Parameter(description = "Page size for pagination", example = "10")
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,

            @Parameter(description = "Field to sort by", example = "id")
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,

            @Parameter(description = "Sorting direction: asc or desc", example = "asc")
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) {
        return ResponseEntity.ok(postService.getAllPosts(pageNo, pageSize, sortBy, sortDir));
    }


    @Operation(
            summary = "Get Post by ID",
            description = "Retrieve a single post using its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post found"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(
            @Parameter(description = "ID of the post to retrieve", example = "1001")
            @PathVariable("id") Long id) {
        return ResponseEntity.ok(postService.getPostsById(id));
    }


    @Operation(
            summary = "Update Post",
            description = "Update an existing blog post by ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post updated successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(
            @Parameter(description = "ID of the post to update", example = "1001")
            @PathVariable("id") Long id,

            @Valid @RequestBody PostDto postDto) {
        return ResponseEntity.ok(postService.updatePost(id, postDto));
    }


    @Operation(
            summary = "Delete Post",
            description = "Delete a blog post by its ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Post deleted successfully"),
            @ApiResponse(responseCode = "404", description = "Post not found")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(
            @Parameter(description = "ID of the post to delete", example = "1")
            @PathVariable("id") Long id) {
        postService.deletePost(id);
        return new ResponseEntity<>("Post Entity Deleted Successfully", HttpStatus.OK);
    }
}