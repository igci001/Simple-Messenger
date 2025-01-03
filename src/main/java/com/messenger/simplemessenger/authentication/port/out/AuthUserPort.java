package com.messenger.simplemessenger.authentication.port.out;

import org.openapitools.model.LoginRequest;

public interface AuthUserPort {
    void registerUser(LoginRequest loginRequest);

}
