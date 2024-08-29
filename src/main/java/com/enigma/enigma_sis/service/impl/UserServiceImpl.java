package com.enigma.enigma_sis.service.impl;

import com.enigma.enigma_sis.constant.ConstantMessage;
import com.enigma.enigma_sis.entity.UserAccount;
import com.enigma.enigma_sis.repository.UserAccountRepository;
import com.enigma.enigma_sis.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserAccountRepository userAccountRepository;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userAccountRepository.findByUsername(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, ConstantMessage.USER_NOT_FOUND
                ));
    }

    @Transactional(readOnly = true)
    @Override
    public UserAccount getByUserId(String id) {
        return userAccountRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND,
                        ConstantMessage.USER_NOT_FOUND)
        );
    }
}
