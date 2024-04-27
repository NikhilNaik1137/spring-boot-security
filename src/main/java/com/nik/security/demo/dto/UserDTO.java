package com.nik.security.demo.dto;

public class UserDTO {

    private String name;

    private String password;

    private UserRoles[] roles;

    public UserDTO() {
    }

    public UserDTO(String name, String password, UserRoles[] roles) {
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRoles[] getRoles() {
        return roles;
    }

    public void setRoles(UserRoles[] roles) {
        this.roles = roles;
    }
}
