package org.palad.fakeshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.palad.fakeshop.controller.exception.UserNotFoundException;
import org.palad.fakeshop.domain.user.User;
import org.palad.fakeshop.dto.user.UserDTO;
import org.palad.fakeshop.infra.repository.UserRepository;
import org.palad.fakeshop.service.UserService;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<UserDTO> getList() {
        List<UserDTO> dtoList = new ArrayList<>();
        List<User> userList = userRepository.findAll();
        userList.forEach(user -> dtoList.add(modelMapper.map(user, UserDTO.class)));
        return dtoList;
}

    @Override
    public UserDTO getUserById(String uid) {
        User user = userRepository.findById(Long.parseLong(uid)).orElseThrow(() -> new UserNotFoundException("User not found : " + uid));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsersWithLimit(String limit) {

        if(!limit.matches("\\d+")) {
            throw new IllegalArgumentException("limit의 값은 숫자만 입력해주세요");
        }

        Pageable pageable = PageRequest.of(0, Integer.parseInt(limit));

        List<User> list = userRepository.findAll(pageable).toList();

        List<UserDTO> dtoList = list.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public List<UserDTO> getUsersWithSort(String sort) {


        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IllegalArgumentException("sort의 값은 'desc', 'asc' 외엔 입력할 수 없습니다.");
        }

        Sort orders = null;

        if(sort.equals("desc")) {
            orders = Sort.by("uid").descending();
        } else {
            orders = Sort.by("uid").ascending();
        }



        Pageable pageable = PageRequest.of(0, 10, orders);

        List<User> list = userRepository.findAll(pageable).toList();

        List<UserDTO> dtoList = list.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public List<UserDTO> getUsersWithLimitAndSort(String limit, String sort) {
        if (!(sort.equals("asc") || sort.equals("desc"))) {
            throw new IllegalArgumentException("sort의 값은 'desc', 'asc' 외엔 입력할 수 없습니다.");
        }

        if(!limit.matches("\\d+")) {
            throw new IllegalArgumentException("limit의 값은 숫자만 입력해주세요");
        }

        Sort orders = null;

        if(sort.equals("desc")) {
            orders = Sort.by("uid").descending();
        } else {
            orders = Sort.by("uid").ascending();
        }

        Pageable pageable = PageRequest.of(0, Integer.parseInt(limit), orders);

        List<User> userGroup = userRepository.findAll(pageable).toList();

        List<UserDTO> userDTOGroup = userGroup.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return userDTOGroup;
    }

    @Override
    public UserDTO addUser(UserDTO userDTO) {

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        User addedUser = userRepository.save(user);
        UserDTO dto = modelMapper.map(addedUser, UserDTO.class);
        return dto;
    }

    @Override
    public UserDTO updateUser(UserDTO userDTO) {
        if(userDTO.getUid() == null) {
            throw new RuntimeException("uid 필드가 반드시 필요합니다. : ");
        }

        User user1 = userRepository.findById(userDTO.getUid()).orElseThrow(
                () -> new UserNotFoundException("User not found : " + userDTO.getUid()));

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        User addedUser = userRepository.save(user);
        UserDTO dto = modelMapper.map(addedUser, UserDTO.class);
        return dto;
    }

    @Override
    public UserDTO deleteUser(String uid) {

        User user = userRepository.findById(Long.valueOf(uid)).orElseThrow(() -> new UserNotFoundException("User not found : " + uid));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userRepository.deleteById(Long.valueOf(uid));
        return userDTO;
    }
}
