package com.enigma.enigma_sis.service;

import com.enigma.enigma_sis.dto.response.JwtClaims;
import com.enigma.enigma_sis.entity.UserAccount;

public interface JwtService {
    String generateToken(UserAccount userAccount);
    boolean validateToken(String token);
    JwtClaims getClaimsByToken(String token);
}
