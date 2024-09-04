package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.UserRole;
import com.enigma.enigma_sis.dto.request.AuthRequest;
import com.enigma.enigma_sis.dto.response.LoginResponse;
import com.enigma.enigma_sis.dto.response.RegisterResponse;
import com.enigma.enigma_sis.entity.Role;
import com.enigma.enigma_sis.entity.Student;
import com.enigma.enigma_sis.entity.Teacher;
import com.enigma.enigma_sis.entity.UserAccount;
import com.enigma.enigma_sis.repository.UserAccountRepository;
import com.enigma.enigma_sis.service.*;
import com.enigma.enigma_sis.util.ValidationUtil;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserAccountRepository userAccountRepository;
    private final RoleService roleService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final JwtService jwtService;

    private final ValidationUtil validationUtil;

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

        Role student = roleService.getOrSave(UserRole.ROLE_STUDENT);
        Role teacher = roleService.getOrSave(UserRole.ROLE_TEACHER);
        Role superAdmin = roleService.getOrSave(UserRole.ROLE_SUPER_ADMIN);

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
        validationUtil.validate(authRequest);
        RegisterResponse response = new RegisterResponse();

        if (authRequest.getEmail().contains("@student.enigma.ac.id")) {
            Role role = roleService.getOrSave(UserRole.ROLE_STUDENT);
            String hashPassword = passwordEncoder.encode(authRequest.getPassword());
            UserAccount userAccount = UserAccount.builder()
                    .username(authRequest.getUsername())
                    .password(hashPassword)
                    .role(List.of(role))
                    .isEnable(true)
                    .build();
            userAccountRepository.saveAndFlush(userAccount);

            Student student = Student.builder()
                    .studentEmail(authRequest.getEmail())
                    .userAccount(userAccount)
                    .build();
            studentService.inputStudent(student);

            response = RegisterResponse.builder()
                    .username(userAccount.getUsername())
                    .roles(userAccount
                            .getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .build();
        } else if (authRequest.getEmail().contains("@teacher.enigma.ac.id")) {
            Role role = roleService.getOrSave(UserRole.ROLE_TEACHER);
            String hashPassword = passwordEncoder.encode(authRequest.getPassword());
            UserAccount userAccount = UserAccount.builder()
                    .username(authRequest.getUsername())
                    .password(hashPassword)
                    .role(List.of(role))
                    .isEnable(true)
                    .build();
            userAccountRepository.saveAndFlush(userAccount);

            Teacher teacher = Teacher.builder()
                    .status(true)
                    .teacherEmail(authRequest.getEmail())
                    .userAccount(userAccount)
                    .build();
            teacherService.inputTeacher(teacher);
            log.debug("Teacher created and saved: {}", teacher);

            response = RegisterResponse.builder()
                    .username(userAccount.getUsername())
                    .roles(userAccount
                            .getAuthorities()
                            .stream()
                            .map(GrantedAuthority::getAuthority)
                            .toList())
                    .build();
        }

        return response;
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
