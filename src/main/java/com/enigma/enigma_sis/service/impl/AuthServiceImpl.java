package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.UserRole;
import com.enigma.enigma_sis.dto.request.AuthRequest;
import com.enigma.enigma_sis.dto.response.LoginResponse;
import com.enigma.enigma_sis.dto.response.RegisterResponse;
import com.enigma.enigma_sis.entity.Role;
import com.enigma.enigma_sis.entity.Student;
import com.enigma.enigma_sis.entity.UserAccount;
import com.enigma.enigma_sis.repository.UserAccountRepository;
import com.enigma.enigma_sis.service.AuthService;
import com.enigma.enigma_sis.service.JwtService;
import com.enigma.enigma_sis.service.RoleService;
import com.enigma.enigma_sis.service.StudentService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final StudentService studentService;
    private final JwtService jwtService;

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Value("${enigma_shop.superadmin.username}")
    private String usernameSuperAdmin;
    @Value("${enigma_shop.superadmin.password}")
    private String passwordSuperAdmin;

    @Transactional(rollbackFor = Exception.class)
    @PostConstruct
    public void initSuperAdmin() {
        Optional<UserAccount> currentSuperAdmin = userAccountRepository.findByUsername(usernameSuperAdmin);
        if (currentSuperAdmin.isPresent()) {
            return;
        }

        Role student = roleService.getOrSave(UserRole.USER_STUDENT);
        Role teacher = roleService.getOrSave(UserRole.USER_TEACHER);
        Role superAdmin = roleService.getOrSave(UserRole.USER_SUPER_ADMIN);

        UserAccount newSuperAdmin = UserAccount.builder()
                .username(usernameSuperAdmin)
                .password(passwordEncoder.encode(passwordSuperAdmin))
                .role(List.of(student, teacher, superAdmin))
                .isEnable(true)
                .build();
        userAccountRepository.save(newSuperAdmin);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public RegisterResponse register(AuthRequest authRequest) throws DataIntegrityViolationException {
        // 1. Membuat dan menyimpan User Account
        Role role = roleService.getOrSave(UserRole.USER_STUDENT);
        String hashPassword = passwordEncoder.encode(authRequest.getPassword());
        UserAccount userAccount = UserAccount.builder()
                .username(authRequest.getUsername())
                .password(hashPassword)
                .role(List.of(role))
                .isEnable(true)
                .build();
        userAccountRepository.saveAndFlush(userAccount);

        // 2. Membuat dan menyimpan Student
        Student student = Student.builder()
                .userAccount(userAccount)
                .build();
        studentService.inputStudent(student);

        return RegisterResponse.builder()
                .username(userAccount.getUsername())
                .roles(userAccount
                        .getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .build();
    }

    @Transactional(readOnly = true)
    @Override
    public LoginResponse login(AuthRequest authRequest) {
        // Daftar User Account
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authRequest.getUsername(), authRequest.getPassword());

        // Validasi User Account
        Authentication authenticate = authenticationManager.authenticate(authentication);

        // Mengubah Authentication ke User Account
        UserAccount userAccount = (UserAccount) authenticate.getPrincipal();

        String token = jwtService.generateToken(userAccount);

        return LoginResponse
                .builder()
                .token(token)
                .username(userAccount.getUsername())
                .roles(userAccount.getAuthorities()
                        .stream()
                        .map(GrantedAuthority::getAuthority)
                        .toList())
                .build();
    }
}
