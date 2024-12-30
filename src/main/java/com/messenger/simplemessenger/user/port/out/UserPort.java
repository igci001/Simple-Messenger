package com.messenger.simplemessenger.user.port.out;

import com.messenger.simplemessenger.user.model.dto.User;
import org.openapitools.model.UserRequest;

import java.util.UUID;

public interface UserPort {
    User findUserById(UUID id);

    User createUser(UserRequest userRequest);
    User updateUser(UserRequest userRequest);
    void deleteUser(UUID id);


}
