package com.blogsApp.Blogs_spring_boot.repository;

import com.blogsApp.Blogs_spring_boot.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CommentRepository extends JpaRepository<Comment, Long > {

}
