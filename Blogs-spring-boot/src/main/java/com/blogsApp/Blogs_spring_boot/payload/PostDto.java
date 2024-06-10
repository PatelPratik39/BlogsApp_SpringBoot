package com.blogsApp.Blogs_spring_boot.payload;

import lombok.Data;

@Data
public class PostDto {
    private long id;
    private String title;
    private String description;
    private String content;
}
