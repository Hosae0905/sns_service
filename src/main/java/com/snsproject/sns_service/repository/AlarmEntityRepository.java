package com.snsproject.sns_service.repository;

import com.snsproject.sns_service.model.entity.AlarmEntity;
import com.snsproject.sns_service.model.entity.LikeEntity;
import com.snsproject.sns_service.model.entity.PostEntity;
import com.snsproject.sns_service.model.entity.UserEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AlarmEntityRepository extends JpaRepository<AlarmEntity, Integer> {

    Page<AlarmEntity> findAllByUserId(Integer userId, Pageable pageable);
//    Page<AlarmEntity> findAllByUser(UserEntity user, Pageable pageable);


}
