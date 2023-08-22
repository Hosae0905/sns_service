package com.snsproject.sns_service.controller.response;

import com.snsproject.sns_service.model.Post;
import com.snsproject.sns_service.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class PostResponse {
    private Integer id;

    private String title;

    private String body;

    private UserResponse user;

    private Timestamp registeredAt;

    private Timestamp updated_at;

    private Timestamp deletedAt;

    public static PostResponse fromPost(Post post) {
        return new PostResponse(
                post.getId(),
                post.getTitle(),
                post.getBody(),
                UserResponse.formUser(post.getUser()),
                post.getRegisteredAt(),
                post.getUpdated_at(),
                post.getDeletedAt()

        );
    }
}
