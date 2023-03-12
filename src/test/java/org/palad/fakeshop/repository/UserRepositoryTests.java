package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;


    @Test
    @DisplayName("User 테이블에 데이터 입력")
    public void insertTest() {

        User user1 = User.builder()
                .email("123@123.com")
                .firstname("firstname")
                .lastname("lastname")
                .joindate(LocalDate.now())
                .username("username")
                .password("password")
                .phone("000-0000-0000")
                .build();

        log.info(user1);

        userRepository.save(user1);

    }

    @Test
    @DisplayName("All User Insert")
    public void allUserInsert() {


        for(int i = 0; i < 100; i++) {

        }

    }

    @Test
    @DisplayName("GET USER BY ID")
    public void selectOneTest() {

        Long userId = 1L;

        User user = userRepository.findById(userId).orElseThrow();

        log.info(user);

    }
}
