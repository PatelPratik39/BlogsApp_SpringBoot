package com.blogsApp.Blogs_spring_boot.repository;

import com.blogsApp.Blogs_spring_boot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long > {

//    find all comments based on Post id
    List<Comment> findByPostId(long postId);

}
