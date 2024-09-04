package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.constant.UserRole;
import com.enigma.enigma_sis.entity.Role;

public interface RoleService {
    Role getOrSave(UserRole role);
}
