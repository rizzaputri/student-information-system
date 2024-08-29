package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.request.AuthRequest;
import com.enigma.enigma_sis.dto.response.RegisterResponse;

public interface AuthService {
    RegisterResponse register(AuthRequest authRequest);
}
