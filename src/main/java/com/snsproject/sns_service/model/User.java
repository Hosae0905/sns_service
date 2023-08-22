package com.snsproject.sns_service.model;

import com.snsproject.sns_service.model.entity.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;

// TODO : implement
@AllArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    private Integer id;
    private String userName;
    private String password;
    private UserRole userRole;
    private Timestamp registered_at;
    private Timestamp updated_at;
    private Timestamp deleted_at;

    public static User fromEntity(UserEntity entity) {
        return new User(
                entity.getId(),
                entity.getUserName(),
                entity.getPassword(),
                entity.getRole(),
                entity.getRegisteredAt(),
                entity.getUpdated_at(),
                entity.getDeletedAt()
        );
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(this.getUserRole().toString()));
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.deleted_at == null;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.deleted_at == null;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.deleted_at == null;
    }

    @Override
    public boolean isEnabled() {
        return this.deleted_at == null;
    }
}
