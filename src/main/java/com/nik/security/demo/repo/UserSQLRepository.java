package com.nik.security.demo.repo;

import com.nik.security.demo.entity.UserSQLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSQLRepository extends JpaRepository<UserSQLEntity, Long> {

    UserSQLEntity findByName(String name);

}
