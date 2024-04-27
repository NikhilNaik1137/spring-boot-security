package com.nik.security.demo.entity;

import com.nik.security.demo.dto.UserRoles;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class UserSQLEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String name;

    private String password;

//    @Enumerated(EnumType.STRING)
    @Column(name = "roles")
    private UserRoles[] roles;

    public UserSQLEntity() {
    }

    public UserSQLEntity(Long id, String name, String password, UserRoles[] roles) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
