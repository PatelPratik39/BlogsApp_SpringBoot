package com.blogsApp.Blogs_spring_boot.service.Impl;

import com.blogsApp.Blogs_spring_boot.entity.Comment;
import com.blogsApp.Blogs_spring_boot.entity.Post;
import com.blogsApp.Blogs_spring_boot.exception.ResourceNotFoundException;
import com.blogsApp.Blogs_spring_boot.payload.CommentDto;
import com.blogsApp.Blogs_spring_boot.repository.CommentRepository;
import com.blogsApp.Blogs_spring_boot.repository.PostRepository;
import com.blogsApp.Blogs_spring_boot.service.CommentService;
import org.springframework.stereotype.Service;

@Service

public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
    }



    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

//        retrieve Post entity by id
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
//        set post to comment entity
        comment.setPost(post);

//        save comment to DB
         Comment newComment = commentRepository.save(comment);
        return mapToDto(newComment);
    }




//    Convert jpa to dto

    private CommentDto mapToDto(Comment comments){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(comments.getId());
        commentDto.setName(comments.getName());
        commentDto.setEmail(comments.getEmail());
        commentDto.setBody(comments.getBody());
        return commentDto;
    }

//    Convert DTO to JPA

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = new Comment();
        comment.setId(commentDto.getId());
        comment.setName(commentDto.getName());
        comment.setEmail(commentDto.getEmail());
        comment.setBody(commentDto.getBody());
        return comment;
    }

}
