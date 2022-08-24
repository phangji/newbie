package com.ssg.newbie.service;

import com.ssg.newbie.domain.user.User;
import com.ssg.newbie.entity.UserEntity;
import com.ssg.newbie.exception.ResourceNotFoundException;
import com.ssg.newbie.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity getEntity(Long id) {
        return userRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("user를 찾을 수 없습니다. userId = " + id));
    }

    public User getUser(Long id) {
        return getEntity(id).toDto();
    }

}
