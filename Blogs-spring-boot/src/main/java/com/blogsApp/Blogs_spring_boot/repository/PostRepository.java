package com.blogsApp.Blogs_spring_boot.repository;

import com.blogsApp.Blogs_spring_boot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;



public interface PostRepository extends JpaRepository<Post, Long> {
//
}
