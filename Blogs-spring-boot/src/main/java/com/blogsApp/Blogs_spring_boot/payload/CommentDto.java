package com.blogsApp.Blogs_spring_boot.payload;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class CommentDto {

    private long id;
    @NotEmpty(message = "Name should not be null or Empty")
    @Size(min = 3)
    private String name;
    @NotEmpty(message = "Email should not be null or Empty")
    @Email
    private String email;
    @NotEmpty(message = "Body should not be null or Empty")
    @Size(min = 10, message = "Body should contain at least 10 characters")
    private String body;

}
