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
@Table(name = "\"user\"")   //따옴표를 붙여주는 이유는 PostgreSQL에는 이미 유저 테이블이 존재한다.
@Getter
@Setter
@SQLDelete(sql = "UPDATE \"user\" SET deleted_at = NOW() where id=?")
@Where(clause = "deleted_at is NULL")
public class UserEntity {
    /**
     * 타임스탬프를 넣어주는 이유는 나중에 cs가 들어오거나 에러가 발생하면 디버깅을 해야되는데
     * 데이터가 언제 저장되었고, 업데이트되고, 삭제 되었는지 정보를 가지고 있어야 확인하기 쉽다.
     *
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private UserRole role = UserRole.USER;        //유저의 권한 정보를 저장

    @Column(name = "registered_at")
    private Timestamp registeredAt;

    @Column(name = "updated_at")
    private Timestamp updated_at;

    @Column(name = "deleted_at")
    private Timestamp deletedAt;

    @PrePersist
    void registeredAt() {
        this.registeredAt = Timestamp.from(Instant.now());
    }

    @PreUpdate
    void updatedAt() {
        this.updated_at = Timestamp.from(Instant.now());
    }

    public static UserEntity of(String userName, String userPassword) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName(userName);
        userEntity.setPassword(userPassword);

        return userEntity;
    }

}
