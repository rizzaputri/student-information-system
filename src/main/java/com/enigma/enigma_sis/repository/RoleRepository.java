package com.enigma.enigma_sis.repository;

import com.enigma.enigma_sis.constant.UserRole;
import com.enigma.enigma_sis.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(UserRole role);
}
