package com.messenger.simplemessenger.user.port.in;

import org.openapitools.model.UserRequest;
import org.openapitools.model.UserResponse;

import java.util.UUID;

public interface UserUseCase {

    UserResponse findUserById(UUID id);

    UserResponse createUser(UserRequest userRequest);
    UserResponse updateUser(UserRequest userRequest);
    void deleteUser(UUID id);
}
