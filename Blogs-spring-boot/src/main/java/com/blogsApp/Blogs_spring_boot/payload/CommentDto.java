package com.blogsApp.Blogs_spring_boot.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommentDto {

    private long id;
    private String name;
    private String email;
    private String body;

}
