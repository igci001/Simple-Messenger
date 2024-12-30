package com.messenger.simplemessenger.user.adapter.in;

import com.messenger.simplemessenger.user.port.in.UserUseCase;
import org.openapitools.api.UsersApi;
import org.openapitools.model.UserRequest;
import org.openapitools.model.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import java.util.UUID;


@Controller("user")
public class UserController implements UsersApi {

    @Autowired
    private UserUseCase userUseCase;

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest userRequest){
        return new ResponseEntity<>(userUseCase.createUser(userRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponse> updateUser(UserRequest userRequest){
        return new ResponseEntity<>(userUseCase.updateUser(userRequest), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<UserResponse> findUser(UUID id){
        return new ResponseEntity<>(userUseCase.findUserById(id), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUser(UUID id){
        userUseCase.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
