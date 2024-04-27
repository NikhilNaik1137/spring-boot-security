package com.nik.security.demo.service;

import com.nik.security.demo.dto.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUserByUsername(String username);

    List<UserDTO> getUsers();

    void createUser(UserDTO user);

    void deleteUser();
}
