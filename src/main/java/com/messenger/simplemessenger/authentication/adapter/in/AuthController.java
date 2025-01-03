package com.messenger.simplemessenger.authentication.adapter.in;

import com.messenger.simplemessenger.authentication.JwtUtils;
import com.messenger.simplemessenger.authentication.model.dto.AuthUser;
import com.messenger.simplemessenger.authentication.port.in.AuthUserUseCase;
import io.swagger.v3.oas.annotations.Parameter;
import org.openapitools.api.RegisterApi;
import org.openapitools.api.SigninApi;
import org.openapitools.model.AuthenticateUser401Response;
import org.openapitools.model.ErrorResponse;
import org.openapitools.model.LoginRequest;
import org.openapitools.model.LoginResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.pulsar.PulsarProperties;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.request.NativeWebRequest;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller("auth")
public class AuthController implements SigninApi, RegisterApi {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private AuthUserUseCase authUserUseCase;

    @Override
    public ResponseEntity<LoginResponse> authenticateUser(LoginRequest loginRequest){


        Authentication authentication;

        try {

            authentication = authenticationManager
                    .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(),loginRequest.getPassword()));
        } catch (AuthenticationException e) {
            ErrorResponse errorResponse = new ErrorResponse("Bad credentials", false);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetails userDetails = (AuthUser) authentication.getPrincipal();

        String jwtToken = jwtUtils.generateTokenFromUsername(userDetails);
        List<String> roles = null;

        if(userDetails.getAuthorities() != null){
           roles  = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());
        }
        LoginResponse response = new LoginResponse(userDetails.getUsername(),roles,jwtToken);

        return ResponseEntity.ok(response);
    }


    private ResponseEntity<ErrorResponse> buildErrorResponse(String message, HttpStatus status) {
        ErrorResponse errorResponse = new ErrorResponse(message, false);
        return ResponseEntity.status(status).body(errorResponse);
    }

    @Override
    public ResponseEntity<Void> registerUser(LoginRequest loginRequest) {
        authUserUseCase.registerUser(loginRequest);
        return ResponseEntity.noContent().build();
    }


    // Required Implementations
    @Override
    public Optional<NativeWebRequest> getRequest() {
        return SigninApi.super.getRequest();
    }

    @Override
    public ResponseEntity<Void> registerUsertest(
            LoginRequest loginRequest
    ) {
        return ResponseEntity.noContent().build();
    }
}
