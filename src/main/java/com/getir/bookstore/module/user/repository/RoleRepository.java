package com.getir.bookstore.module.user.repository;

import com.getir.bookstore.module.user.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
  Optional<Role> findByRoleName(String name);
}
