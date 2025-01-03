package com.messenger.simplemessenger.authentication.adapter.in;

import com.messenger.simplemessenger.authentication.adapter.out.AuthUserAdapter;
import com.messenger.simplemessenger.authentication.port.in.AuthUserUseCase;
import com.messenger.simplemessenger.authentication.port.out.AuthUserPort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.LoginRequest;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class AuthUserCaseImpl implements AuthUserUseCase {

    private final AuthUserPort authUserPort;

    @Override
    public void registerUser(LoginRequest loginRequest) {
        authUserPort.registerUser(loginRequest);
    }
}
