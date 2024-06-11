package com.blogsApp.Blogs_spring_boot.service.Impl;

import com.blogsApp.Blogs_spring_boot.entity.Comment;
import com.blogsApp.Blogs_spring_boot.entity.Post;
import com.blogsApp.Blogs_spring_boot.exception.BlogAPIException;
import com.blogsApp.Blogs_spring_boot.exception.ResourceNotFoundException;
import com.blogsApp.Blogs_spring_boot.payload.CommentDto;
import com.blogsApp.Blogs_spring_boot.repository.CommentRepository;
import com.blogsApp.Blogs_spring_boot.repository.PostRepository;
import com.blogsApp.Blogs_spring_boot.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper mapper;

    public CommentServiceImpl(CommentRepository commentRepository, PostRepository postRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.mapper = mapper;
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
//Get all comments based on Id
    @Override
    public List<CommentDto> getCommentsByPostId(long postId) {

//        retrieve comments by postId
        List<Comment> comments = commentRepository.findByPostId(postId);

        return comments.stream().map(comment -> mapToDto(comment)).collect(Collectors.toList());
    }

//    Get comments by id
    @Override
    public CommentDto getCommentById(Long postId, Long commentId) {
        //        retrieve Post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));

//        retrieve Comment by Id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment","id", commentId));
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the Post");
        }
        return mapToDto(comment);
    }
//Update Comments
    @Override
    public CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest) {
        //        retrieve Post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
//        retrieve Comment by Id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment","id", commentId));
//        If comment is present or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the Post");
        }

        comment.setName(commentRequest.getName());
        comment.setEmail(commentRequest.getEmail());
        comment.setBody(commentRequest.getBody());
       Comment updatedComment =  commentRepository.save(comment);
       return mapToDto(updatedComment);
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
//           retrieve Post entity by id
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException("Post", "id", postId));
//        retrieve Comment by Id
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException("Comment","id", commentId));
//        If comment is present or not
        if(!comment.getPost().getId().equals(post.getId())){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to the Post");
        }
       commentRepository.delete(comment);
    }


//    Convert jpa Entity to dto

    private CommentDto mapToDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
        return commentDto;
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comments.getId());
//        commentDto.setName(comments.getName());
//        commentDto.setEmail(comments.getEmail());
//        commentDto.setBody(comments.getBody());
//        return commentDto;
    }

//    Convert DTO to JPA

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto,Comment.class);
//        Comment comment = new Comment();
//        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }

}
