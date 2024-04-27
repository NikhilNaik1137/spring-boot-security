package com.nik.security.demo.config;

import com.nik.security.demo.dto.UserDTO;
import com.nik.security.demo.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserService userService;

//    private final PasswordEncoder passwordEncoder;

    CustomUserDetailsService(UserService userService) {
//    CustomUserDetailsService(UserService userService, PasswordEncoder passwordEncoder) {
        this.userService = userService;
//        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        return new User("nik", passwordEncoder.encode("nikpass"), List.of(new SimpleGrantedAuthority("devops")));
        UserDTO userDTO = userService.getUserByUsername(username);
        return new User(
                userDTO.getName(),
                userDTO.getPassword(),
                Arrays.stream(userDTO.getRoles()).map(role -> {
                    return new SimpleGrantedAuthority(role.toString());
                }).toList()
        );
    }
}
