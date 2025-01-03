package com.messenger.simplemessenger.authentication.adapter.out;

import com.messenger.simplemessenger.authentication.mapper.AuthUserMapper;
import com.messenger.simplemessenger.authentication.model.AuthUserEntity;
import com.messenger.simplemessenger.authentication.port.out.AuthUserPort;
import com.messenger.simplemessenger.authentication.port.out.AuthUserRepository;
import com.messenger.simplemessenger.user.model.UserEntity;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.LoginRequest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AuthUserAdapter implements AuthUserPort {


    private final AuthUserRepository authUserRepository;
    private final AuthUserMapper authUserMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(LoginRequest loginRequest) {


        loginRequest.setPassword(
                passwordEncoder.encode(loginRequest.getPassword()));

        AuthUserEntity authUser = authUserMapper.toEntity(loginRequest);

        UserEntity user = UserEntity.builder()
                        .firstName(loginRequest.getUsername())
                        .authUser(authUser)
                        .build();

        authUser.setUserDetails(user);


        var test = authUserRepository.save(authUser);

    }
}
