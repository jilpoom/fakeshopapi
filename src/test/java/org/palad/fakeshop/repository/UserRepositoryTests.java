package org.palad.fakeshop.repository;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.domain.user.Address;
import org.palad.fakeshop.domain.user.Name;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.dto.user.UserDTO;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.palad.fakeshop.util.RandomUserGenerater;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
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

    @Test
    @DisplayName("User 테이블 페이징")
    public void pagingTest() {
        int page = 0;
        int size = 10;

        Sort sort = Sort.by("uid").ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        List<User> userGroup = userRepository.findAll(pageable).getContent();

        userGroup.forEach(user -> log.info(user));

    }





    @Test
    @DisplayName("User 테이블에 데이터 입력")
    public void insertTest() {

        User user1 = User.builder()
                .email("123@123.com")
                .phone("010-1111-1111")
                .password(passwordEncoder.encode("1234"))
                .joindate(LocalDate.now())
                .username("user00")
                .build();

        log.info(user1);

        userRepository.save(user1);

    }

    @Test
    @DisplayName("All User Insert")
    public void allUserInsert() {
        for(int i = 0; i < 100; i++) {

            Address address = Address.builder()
                    .zipcode(RandomUserGenerater.getRandomZipCode())
                    .latitude(new Random().nextDouble())
                    .longitude(new Random().nextDouble())
                    .city(RandomUserGenerater.getRandomAddress())
                    .build();

            Name name = Name.builder()
                    .firstname(RandomUserGenerater.getRandomKoreanFirstName())
                    .lastname(RandomUserGenerater.getRandomKoreanLastName())
                    .build();

            User user1 = User.builder()
                    .email("user" + i + "@" + "naver.com")
                    .address(address)
                    .name(name)
                    .phone(RandomUserGenerater.getRandomPhoneNumber())
                    .password(passwordEncoder.encode("1234"))
                    .joindate(LocalDate.now())
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

    @Test
    @DisplayName("Set Role")
    public void setRoleTest() {

    }

}
