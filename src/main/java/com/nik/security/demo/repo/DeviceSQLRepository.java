package com.nik.security.demo.repo;

import com.nik.security.demo.entity.DeviceSQLEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceSQLRepository extends JpaRepository<DeviceSQLEntity, Long> {
}
