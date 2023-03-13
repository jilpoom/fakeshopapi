package org.palad.fakeshop.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.palad.fakeshop.dto.UserDTO;
import org.palad.fakeshop.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping()
    public List<UserDTO> getAllUser(@RequestParam(required = false) String limit,
                                    @RequestParam(required = false) String sort) {

        if(limit != null) {
            return userService.getUsersWithLimit(Integer.parseInt(limit));
        }

        if(sort != null) {
            if(sort.equals("desc")) {
                Sort desc = Sort.by("uid").descending();
                return userService.getUsersWithSort(desc);
            }
        }

        return userService.getList();
    }

    @GetMapping("/{uid}")
    public UserDTO getUser(@PathVariable String uid) {
        return userService.getUserById(Long.valueOf(uid));
    }

    @PostMapping()
    public UserDTO getUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @PutMapping()
    public UserDTO updateUser(@RequestBody UserDTO userDTO) {return userService.updateUser(userDTO); };

    @DeleteMapping("/{uid}")
    public UserDTO deleteUser(@PathVariable String uid) {

        return userService.deleteUser(uid);
    }

}
