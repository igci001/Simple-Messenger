package com.messenger.simplemessenger.user.adapter.out;

import com.messenger.simplemessenger.user.UserMapper;
import com.messenger.simplemessenger.user.model.dto.User;
import com.messenger.simplemessenger.user.port.out.UserRepository;
import com.messenger.simplemessenger.user.port.out.UserPort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.UserRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;
@RequiredArgsConstructor
@Transactional
@Service
public class UserAdapter implements UserPort {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public User findUserById(UUID id) {
        return userMapper.toDomain(
                userRepository.findById(id)
                        .orElseThrow(
                                () -> new NoSuchElementException("User with id: %s not found".formatted(id)
                                )
                        )
        );
    }

    @Override
    public User createUser(UserRequest userRequest) {

        var user = userMapper.toEntity(userRequest);


        return userMapper.toDomain(user);
    }

    @Override
    public User updateUser(UserRequest userRequest) {
        if(userRequest.getId() == null)
            throw new IllegalArgumentException("Id cant be null");

        if(!userRepository.existsById(userRequest.getId()))
            throw new NoSuchElementException("User with id: %s not found".formatted(userRequest.getId()));

        return userMapper.toDomain(userRepository.save(userMapper.toEntity(userRequest)));
    }

    @Override
    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
