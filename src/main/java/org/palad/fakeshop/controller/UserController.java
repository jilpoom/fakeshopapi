package org.palad.fakeshop.controller;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    @ApiOperation("Sample GET users")
    @GetMapping("/douser")
    public Map<String, Object> doUser() {

        return Map.of("user1", "12345", "password", "12345");
    }






}
