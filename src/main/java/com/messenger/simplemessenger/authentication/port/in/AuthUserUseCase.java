package com.messenger.simplemessenger.authentication.port.in;

import org.openapitools.model.LoginRequest;

public interface AuthUserUseCase {

    void registerUser(LoginRequest loginRequest);
}
