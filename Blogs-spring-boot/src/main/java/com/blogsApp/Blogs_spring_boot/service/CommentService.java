package com.blogsApp.Blogs_spring_boot.service;

import com.blogsApp.Blogs_spring_boot.payload.CommentDto;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto commentDto);
}
