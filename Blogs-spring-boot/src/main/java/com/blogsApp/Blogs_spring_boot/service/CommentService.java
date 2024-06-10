package com.blogsApp.Blogs_spring_boot.service;

import com.blogsApp.Blogs_spring_boot.payload.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);

//    get all comments
    List<CommentDto> getCommentsByPostId(long postId);

//    Get Comments by Id
    CommentDto getCommentById(Long postId, Long commentId);

//    Update Comment based on PostId
    CommentDto updateComment(Long postId, Long commentId, CommentDto commentRequest);
//    Delete Comments
    void deleteComment(Long postId, Long commentId);
}
