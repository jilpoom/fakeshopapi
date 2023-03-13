package org.palad.fakeshop.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.dto.UserDTO;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.palad.fakeshop.util.RandomUserGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.Random;

@SpringBootTest
@Log4j2
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @DisplayName("User 테이블에 데이터 입력")
    public void insertTest() {

        User user1 = User.builder()
                .city("서울특별시 뭔구 뭔동 뭔 번지")
                .email("123@123.com")
                .firstname("홍")
                .lastname("길동")
                .latitude(new Random().nextDouble())
                .longitude(new Random().nextDouble())
                .phone("010-1111-1111")
                .password(passwordEncoder.encode("1234"))
                .joindate(LocalDate.now())
                .zipcode("34253")
                .username("user00")
                .build();

        log.info(user1);

        userRepository.save(user1);

    }

    @Test
    @DisplayName("All User Insert")
    public void allUserInsert() {
        for(int i = 0; i < 100; i++) {

            User user1 = User.builder()
                    .city(RandomUserGenerater.getRandomAddress())
                    .email("user" + i + "@" + "naver.com")
                    .firstname(RandomUserGenerater.getRandomKoreanFirstName())
                    .lastname(RandomUserGenerater.getRandomKoreanLastName())
                    .latitude(new Random().nextDouble())
                    .longitude(new Random().nextDouble())
                    .phone(RandomUserGenerater.getRandomPhoneNumber())
                    .password(passwordEncoder.encode("1234"))
                    .joindate(LocalDate.now())
                    .zipcode(RandomUserGenerater.getRandomZipCode())
                    .username("user" + i)
                    .build();

            log.info(user1);

            userRepository.save(user1);

        }


    }

    @Test
    @DisplayName("GET USER BY ID")
    public void selectOneTest() {

        Long userId = 1L;

        User user = userRepository.findById(userId).orElseThrow();

        log.info(user);

    }

    @Test
    @DisplayName("ADD USER")
    public void addUserTest() {

        User user = userRepository.findById(1L).orElseThrow();
        log.info(user);
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        log.info(userDTO);
        userDTO.setUid(null);
        log.info(userDTO);
        User toAddUser = modelMapper.map(userDTO, User.class);
        log.info(toAddUser);

        userRepository.save(toAddUser);


    }
}
