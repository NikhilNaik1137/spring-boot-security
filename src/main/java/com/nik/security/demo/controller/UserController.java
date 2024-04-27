package com.nik.security.demo.controller;

import com.nik.security.demo.dto.UserDTO;
import com.nik.security.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity getAllUsers() {
        try {
            return ResponseEntity.ok(userService.getUsers());
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unable to retrieve all users");
        }
    }

    @PostMapping("create")
    public ResponseEntity createUser(@RequestBody UserDTO userDTO) {
        try {
            userService.createUser(userDTO);
            return ResponseEntity.ok().body("Created User Successfully");
        }
        catch (Exception e) {
            return ResponseEntity.internalServerError().body("Unable to create user");
        }
    }
}
