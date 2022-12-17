package com.ssg.newbie.repository;

import com.ssg.newbie.constant.UserClubType;
import com.ssg.newbie.entity.UserEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@ActiveProfiles("test")
@DataJpaTest    // h2로 데이터베이스 생성
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    void test_findByClubType() {
        // given
        saveUser("일반유저", UserClubType.NORMAL);
        saveUser("스마일유저", UserClubType.SMILE_CLUB);

        // when
        List<UserEntity> result = userRepository.findByClubType(UserClubType.NORMAL);

        // then
        Assertions.assertEquals(result.size(), 1);
        UserEntity user = result.get(0);
        Assertions.assertEquals(user.getName(), "일반유저");
        Assertions.assertEquals(user.getClubType(), UserClubType.NORMAL);
    }

    private void saveUser(String name, UserClubType clubType) {
        UserEntity entity = new UserEntity();
        entity.setName(name);
        entity.setClubType(clubType);
        userRepository.save(entity);
    }
}
