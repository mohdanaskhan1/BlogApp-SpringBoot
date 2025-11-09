package com.example.blogapp.service;

import com.example.blogapp.payload.PostDto;
import com.example.blogapp.payload.PostResponse;

public interface PostService {

    PostDto createPost(PostDto postDto);

//    List<PostDto> getAllPosts(int pageNo, int pageSize);

    PostResponse getAllPosts(int pageNo, int pageSize , String sortBy, String sortDir);


    PostDto getPostsById(Long id);

    PostDto updatePost(Long id, PostDto postDto);

    void deletePost(Long id);
}
