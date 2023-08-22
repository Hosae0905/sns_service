package com.snsproject.sns_service.model;

import com.snsproject.sns_service.model.entity.PostEntity;
import com.snsproject.sns_service.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;


import java.sql.Timestamp;

@Getter
@AllArgsConstructor
public class Post {

    private Integer id;

    private String title;

    private String body;

    private User user;

    private Timestamp registeredAt;

    private Timestamp updated_at;

    private Timestamp deletedAt;

    public static Post fromEntity(PostEntity entity) {
        return new Post(
                entity.getId(),
                entity.getTitle(),
                entity.getBody(),
                User.fromEntity(entity.getUser()),
                entity.getRegisteredAt(),
                entity.getUpdated_at(),
                entity.getDeletedAt()
        );
    }
}
