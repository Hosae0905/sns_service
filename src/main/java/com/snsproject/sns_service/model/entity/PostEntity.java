package com.snsproject.sns_service.model.entity;

import com.snsproject.sns_service.model.UserRole;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table(name = "\"post\"")   //따옴표를 붙여주는 이유는 PostgreSQL에는 이미 유저 테이블이 존재한다.
@Getter
@Setter
@SQLDelete(sql = "UPDATE \"post\" SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class PostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;        //post를 가져올 때 user도 같이 가져올 수 있게 해준다.

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updatedAt")
    private Timestamp updatedAt;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updatedAt = Timestamp.from(Instant.now());
    }

    public static PostEntity of(String title, String body, UserEntity userEntity) {
        PostEntity entity = new PostEntity();

        entity.setTitle(title);
        entity.setBody(body);
        entity.setUser(userEntity);

        return entity;
    }
}
