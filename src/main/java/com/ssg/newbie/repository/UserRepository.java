package com.ssg.newbie.repository;

import com.ssg.newbie.constant.UserClubType;
import com.ssg.newbie.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByClubType(UserClubType clubType);
}
