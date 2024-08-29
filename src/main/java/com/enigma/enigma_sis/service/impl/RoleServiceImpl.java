package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.UserRole;
import com.enigma.enigma_sis.entity.Role;
import com.enigma.enigma_sis.repository.RoleRepository;
import com.enigma.enigma_sis.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Role getOrSave(UserRole role) {
        return roleRepository.findByRole(role).orElseGet(
                () -> roleRepository.saveAndFlush(
                        Role
                                .builder()
                                .role(role)
                                .build()
                )
        );
    }
}
