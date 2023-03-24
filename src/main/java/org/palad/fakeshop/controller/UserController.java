package org.palad.fakeshop.controller;

import lombok.RequiredArgsConstructor;
import org.palad.fakeshop.dto.user.UserDTO;
import org.palad.fakeshop.service.UserService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<?> getUser(@PathVariable String uid) {
        try {
            UserDTO userDTO = userService.getUserById(Long.valueOf(uid));

            return ResponseEntity.ok()
                    .body(userDTO);

        } catch (RuntimeException runtimeException) {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("uid", runtimeException.getMessage()));
        }
    }

    @PostMapping()
    public ResponseEntity<?> getUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Map<String, String> errors = bindingErrorReturner(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }

        UserDTO dto = userService.addUser(userDTO);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(dto);
    }

    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDTO userDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            Map<String, String> errors = bindingErrorReturner(bindingResult);
            return ResponseEntity.badRequest().body(errors);
        }
        try {
            UserDTO dto = userService.updateUser(userDTO);

            return ResponseEntity.status(HttpStatus.ACCEPTED).body(dto);
        } catch (RuntimeException runtimeException) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("uid", runtimeException.getMessage()));
        }


    };

    @DeleteMapping("/{uid}")
    public UserDTO deleteUser(@PathVariable String uid) {
        return userService.deleteUser(uid);
    }


    private Map<String, String> bindingErrorReturner(BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            Map<String, String> errors = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errors.put(error.getField(),error.getDefaultMessage());
            }
            return errors;
        }

        return null;
    }


}
