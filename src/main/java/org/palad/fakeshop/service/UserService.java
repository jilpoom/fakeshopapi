package org.palad.fakeshop.service;

import org.palad.fakeshop.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getList();

    UserDTO getUserById(String uid);

    List<UserDTO> getUsersWithLimit(String limit);

    List<UserDTO> getUsersWithSort(String sort);

    List<UserDTO> getUsersWithLimitAndSort(String limit, String sort);

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO deleteUser(String uid);
}
