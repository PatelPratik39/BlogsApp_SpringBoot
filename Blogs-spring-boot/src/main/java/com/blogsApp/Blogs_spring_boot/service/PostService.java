package com.blogsApp.Blogs_spring_boot.service;

import com.blogsApp.Blogs_spring_boot.payload.PostDto;

import java.util.List;

public interface PostService  {

//    Create a Post
    PostDto createPost(PostDto postDto);

//    getAll Posts
    List<PostDto> getAllPosts();

//    Get a Post by Id
    PostDto getPostById(long id);

//    Update a Post using id
    PostDto updatePost(PostDto postDto, long id);

//    Delete Post using id
    void deletePost(long id);
}
