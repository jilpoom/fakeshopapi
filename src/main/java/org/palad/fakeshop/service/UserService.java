package org.palad.fakeshop.service;

import org.palad.fakeshop.dto.user.UserDTO;

import java.util.List;

public interface UserService {

    List<UserDTO> getList();

    UserDTO getUserById(String uid);

    /**
     * 23-03-27 14:23 Deprecated : <br>
     * getUsersWithLimitAndSort 메소드 사용 권장
     * @param limit
     * @return List
     */
    @Deprecated
    List<UserDTO> getUsersWithLimit(String limit);

    /**
     * 23-03-27 14:23 Deprecated : <br>
     * getUsersWithLimitAndSort 메소드 사용 권장
     * @param sort
     * @return List
     */
    @Deprecated
    List<UserDTO> getUsersWithSort(String sort);

    List<UserDTO> getUsersWithLimitAndSort(String limit, String sort);

    UserDTO addUser(UserDTO userDTO);

    UserDTO updateUser(UserDTO userDTO);

    UserDTO deleteUser(String uid);
}
