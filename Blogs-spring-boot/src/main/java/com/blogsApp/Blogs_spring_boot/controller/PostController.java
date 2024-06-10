package com.blogsApp.Blogs_spring_boot.controller;

import com.blogsApp.Blogs_spring_boot.payload.PostDto;
import com.blogsApp.Blogs_spring_boot.payload.PostResponse;
import com.blogsApp.Blogs_spring_boot.service.PostService;
import com.blogsApp.Blogs_spring_boot.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.blogsApp.Blogs_spring_boot.utils.AppConstants.DEFAULT_PAGE_NUMBER;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

//    Create Blog Post API
    @PostMapping
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto){
        return new ResponseEntity<>(postService.createPost(postDto), HttpStatus.CREATED);
    }

//    GetAllPost REST API
//Sorting, pagination parameters
    @GetMapping
    public PostResponse getAllPosts(
            @RequestParam(value = "pageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pageSize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam(value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ){
        return postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
    }

//    Get Post By ID using REST API
    @GetMapping("/{id}")
    public ResponseEntity<PostDto> getPostById(@PathVariable("id") long postId){
        return ResponseEntity.ok(postService.getPostById(postId));
    }

//    Update Post by id REST API
    @PutMapping("/{id}")
    public ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable("id") long postId){
        PostDto postResponse = postService.updatePost(postDto,postId);
        return  new ResponseEntity<>(postResponse, HttpStatus.OK);
    }
//    Delete Post REST API
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") long postId){
        postService.deletePost(postId);
        String responseMessage = postId + " Deleted Successfully";
        return new ResponseEntity<>(responseMessage , HttpStatus.OK);
    }
}
