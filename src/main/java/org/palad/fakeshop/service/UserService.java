package org.palad.fakeshop.service;

import org.palad.fakeshop.dto.user.UserDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface UserService {

    List<UserDTO> getList();

    UserDTO getUserById(Long uid);

    List<UserDTO> getUsersWithLimit(int limit);

    List<UserDTO> getUsersWithSort(Sort sort);

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO deleteUser(String uid);
}
