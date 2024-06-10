package com.blogsApp.Blogs_spring_boot.service.Impl;

import com.blogsApp.Blogs_spring_boot.entity.Post;
import com.blogsApp.Blogs_spring_boot.exception.ResourceNotFoundException;
import com.blogsApp.Blogs_spring_boot.payload.PostDto;
import com.blogsApp.Blogs_spring_boot.repository.PostRepository;
import com.blogsApp.Blogs_spring_boot.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto createPost(PostDto postDto) {
//        Convert a DTO object to JPA Entity
//        Post post = new Post();
//        post.setId(postDto.getId());
//        post.setTitle(postDto.getTitle());
//        post.setDescription(postDto.getDescription());
//        post.setContent(postDto.getContent());
        Post post = mapToEntity(postDto);
        Post newPost = postRepository.save(post);

//        Convert a JPA Entity to DTO
        PostDto postResponse = mapToDTO(newPost);
        return postResponse;
    }

    @Override
    public List<PostDto> getAllPosts() {
        List<Post> posts =  postRepository.findAll();
        return posts.stream().map(post ->  mapToDTO(post)).collect(Collectors.toList());

    }

    @Override
    public PostDto getPostById(long id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDto updatePost(PostDto postDto, long id) {
//        Get a Post by id from a database
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

       Post updatedPost = postRepository.save(post);
       return mapToDTO(updatedPost);
    }

    @Override
    public void deletePost(long id) {
//        Get a Post by id from a database
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    //    convert Entity to DTO
    private PostDto mapToDTO(Post post){
        PostDto postDto = new PostDto();
        postDto.setId(post.getId());
        postDto.setTitle(post.getTitle());
        postDto.setContent(post.getContent());
        postDto.setDescription(post.getDescription());
        return postDto;
    }

//    Convert DTO to Entity
    private Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());
        return post;

    }
}
