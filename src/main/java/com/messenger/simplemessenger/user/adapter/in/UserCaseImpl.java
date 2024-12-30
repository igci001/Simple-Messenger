package com.messenger.simplemessenger.user.adapter.in;

import com.messenger.simplemessenger.user.UserMapper;
import com.messenger.simplemessenger.user.port.in.UserUseCase;
import com.messenger.simplemessenger.user.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.UserRequest;
import org.openapitools.model.UserResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class UserCaseImpl implements UserUseCase {

    private final UserPort userPort;
    private final UserMapper userMapper;

    @Override
    public UserResponse findUserById(UUID id) {
        return userMapper.toResponse(userPort.findUserById(id));
    }

    @Override
    public UserResponse createUser(UserRequest userRequest) {
        return userMapper.toResponse(userPort.createUser(userRequest));
    }

    @Override
    public UserResponse updateUser(UserRequest userRequest) {
        return userMapper.toResponse(userPort.updateUser(userRequest));
    }

    @Override
    public void deleteUser(UUID id) {
        userPort.deleteUser(id);
    }
}
