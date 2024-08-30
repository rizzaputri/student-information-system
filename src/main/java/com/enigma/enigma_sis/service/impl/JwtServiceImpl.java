package com.enigma.enigma_sis.service.impl;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.enigma.enigma_sis.dto.response.JwtClaims;
import com.enigma.enigma_sis.entity.UserAccount;
import com.enigma.enigma_sis.service.JwtService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;

@Service
@Slf4j
public class JwtServiceImpl implements JwtService {
    private final String JWT_SECRET;
    private final String ISSUER;
    private final long JWT_EXPIRATION;

    public JwtServiceImpl(
            @Value("${enigma_shop.jwt.secret_key}") String JWT_SECRET,
            @Value("${enigma_shop.jwt.issuer}") String ISSUER,
            @Value("${enigma_shop.jwt.expiration_in_second}") long JWT_EXPIRATION
    ) {
        this.JWT_SECRET = JWT_SECRET;
        this.ISSUER = ISSUER;
        this.JWT_EXPIRATION = JWT_EXPIRATION;
    }

    @Override
    public String generateToken(UserAccount userAccount) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            return JWT.create()
                    .withSubject(userAccount.getId())
                    .withClaim("roles", userAccount.getAuthorities()
                            .stream().map(GrantedAuthority::getAuthority)
                            .toList())
                    .withIssuedAt(Instant.now())
                    .withExpiresAt(Instant.now()
                            .plusSeconds(JWT_EXPIRATION))
                    .withIssuer(ISSUER)
                    .sign(algorithm);
        } catch (JWTCreationException e) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Error while creating JWT Token"
            );
        }
    }

    @Override
    public boolean validateToken(String bearerToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            String token = parseJWT(bearerToken);
            assert token != null;
            verifier.verify(token);
            return true;
        } catch (JWTCreationException e) {
            log.error("Invalid JWT Signature: {}", e.getMessage());
            return false;
        }
    }

    @Override
    public JwtClaims getClaimsByToken(String bearerToken) {
        try {
            Algorithm algorithm = Algorithm.HMAC512(JWT_SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer(ISSUER)
                    .build();

            String token = parseJWT(bearerToken);
            assert token != null;
            DecodedJWT decodedJWT = verifier.verify(token);
            return JwtClaims
                    .builder()
                    .userAccountId(decodedJWT.getSubject())
                    .roles(decodedJWT.getClaim("roles").asList(String.class))
                    .build();
        } catch (JWTVerificationException e) {
            log.error("Invalid JWT Claims: {}", e.getMessage());
            return null;
        }
    }

    private String parseJWT(String token) {
        log.error("Test token: {}", token);
        if (token != null && token.startsWith("Bearer ")) {
            return token.substring(7);
        }
        return null;
    }
}
