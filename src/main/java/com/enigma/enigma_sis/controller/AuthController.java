package com.enigma.enigma_sis.controller;

import com.enigma.enigma_sis.constant.ApiUrl;
import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.dto.request.AuthRequest;
import com.enigma.enigma_sis.dto.response.CommonResponse;
import com.enigma.enigma_sis.dto.response.LoginResponse;
import com.enigma.enigma_sis.dto.response.RegisterResponse;
import com.enigma.enigma_sis.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = ApiUrl.AUTH_URL)
public class AuthController {
    private final AuthService authService;

    @PostMapping(path = ApiUrl.AUTH_REGISTER)
    public ResponseEntity<CommonResponse<?>> registerUser(
            @RequestBody AuthRequest authRequest) {
        RegisterResponse register = authService.register(authRequest);
        CommonResponse<RegisterResponse> response = CommonResponse
                .<RegisterResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ConstantMessage.REGISTER_SUCCES)
                .data(register)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping(path = ApiUrl.AUTH_LOGIN)
    public ResponseEntity<CommonResponse<?>> loginUser(
            @RequestBody AuthRequest authRequest) {
        LoginResponse login = authService.login(authRequest);
        CommonResponse<LoginResponse> response = CommonResponse
                .<LoginResponse>builder()
                .statusCode(HttpStatus.CREATED.value())
                .message(ConstantMessage.LOGIN_SUCCES)
                .data(login)
                .build();
        return ResponseEntity.ok(response);
    }
}
