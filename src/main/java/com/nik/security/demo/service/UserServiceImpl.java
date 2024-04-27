package com.nik.security.demo.service;


import com.nik.security.demo.dto.UserDTO;
import com.nik.security.demo.entity.UserSQLEntity;
import com.nik.security.demo.repo.UserSQLRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;

    private final UserSQLRepository userSQLRepository;

    public UserServiceImpl(PasswordEncoder passwordEncoder, UserSQLRepository userSQLRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userSQLRepository = userSQLRepository;
    }

    @Override
    public UserDTO getUserByUsername(String username) {
        UserSQLEntity userEntity = userSQLRepository.findByName(username);
        if (userEntity == null)
            throw new UsernameNotFoundException("Username %s not found".formatted(username));

        UserDTO userDTO = new UserDTO();
        BeanUtils.copyProperties(userEntity, userDTO);
        return userDTO;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserSQLEntity> userEntities = userSQLRepository.findAll();
        List<UserDTO> userDTOs = userEntities.stream().map((userEntity) -> {
            UserDTO userDTO = new UserDTO();
            BeanUtils.copyProperties(userEntity, userDTO);
            return userDTO;
        }).toList();
        return userDTOs;
    }

    @Override
    public void createUser(UserDTO userDTO) {
        Assert.notNull(userDTO, "User request cannot be null");
        UserSQLEntity userEntity = new UserSQLEntity();
        BeanUtils.copyProperties(userDTO, userEntity);

        String encodedPassword = passwordEncoder.encode(userDTO.getPassword());
        userEntity.setPassword(encodedPassword);

        userSQLRepository.save(userEntity);
    }

    @Override
    public void deleteUser() {

    }
}
