package org.palad.fakeshop.controller;

import lombok.RequiredArgsConstructor;
import org.palad.fakeshop.controller.exception.BindingResultMapper;
import org.palad.fakeshop.controller.exception.UserNotFoundException;
import org.palad.fakeshop.dto.user.UserDTO;
import org.palad.fakeshop.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    private final BindingResultMapper bindingResultMapper;

    @GetMapping()
    public List<UserDTO> getAllUser(@RequestParam(required = false) String limit,
                                    @RequestParam(required = false) String sort) throws IllegalArgumentException {

        if (limit != null && sort != null) {
            return userService.getUsersWithLimitAndSort(limit, sort);
        }

        if (limit != null) {
            return userService.getUsersWithLimit(limit);
        }

        if (sort != null) {
            return userService.getUsersWithSort(sort);
        }

        return userService.getList();
    }


    @GetMapping("/{uid}")
    public UserDTO getUser(@PathVariable String uid) throws UserNotFoundException {
        return userService.getUserById(uid);
    }

    @PostMapping()
    public ResponseEntity<?> addUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResultMapper.check(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }

        UserDTO dto = userService.addUser(userDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) throws UserNotFoundException {
        if (bindingResult.hasErrors()) {
            Map<String, String> errors = bindingResultMapper.check(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }

        UserDTO dto = userService.updateUser(userDTO);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
    }

    @DeleteMapping("/{uid}")
    public UserDTO deleteUser(@PathVariable String uid) throws UserNotFoundException{
        return userService.deleteUser(uid);
    }


    private boolean isDesc(String sort) {
        return sort.equals("desc");
    }

    private boolean isAsc(String sort) {
        return sort.equals("asc");
    }


}
