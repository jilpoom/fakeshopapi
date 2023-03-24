package org.palad.fakeshop.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
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
    public UserDTO getUserById(Long uid) {
        User user = userRepository.findById(uid).orElseThrow(() -> new RuntimeException("User not found : " + uid));
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsersWithLimit(int limit) {
        Pageable pageable = PageRequest.of(0, limit);

        List<User> list = userRepository.findAll(pageable).toList();

        List<UserDTO> dtoList = list.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    @Override
    public List<UserDTO> getUsersWithSort(Sort sort) {
        Pageable pageable = PageRequest.of(0, 10, sort);

        List<User> list = userRepository.findAll(pageable).toList();

        List<UserDTO> dtoList = list.stream()
                .map(user -> modelMapper.map(user, UserDTO.class))
                .collect(Collectors.toList());

        return dtoList;
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
                () -> new RuntimeException("User not found : " + userDTO.getUid()));

        userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        User user = modelMapper.map(userDTO, User.class);
        User addedUser = userRepository.save(user);
        UserDTO dto = modelMapper.map(addedUser, UserDTO.class);
        return dto;
    }

    @Override
    public UserDTO deleteUser(String uid) {
        User user = userRepository.findById(Long.valueOf(uid)).orElseThrow();
        UserDTO userDTO = modelMapper.map(user, UserDTO.class);
        userRepository.deleteById(Long.valueOf(uid));
        return userDTO;
    }
}
