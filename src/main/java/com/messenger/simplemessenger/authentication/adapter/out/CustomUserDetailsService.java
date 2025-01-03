package com.messenger.simplemessenger.authentication.adapter.out;

import com.messenger.simplemessenger.authentication.mapper.AuthUserMapper;
import com.messenger.simplemessenger.authentication.model.AuthUserEntity;
import com.messenger.simplemessenger.authentication.port.out.AuthUserRepository;
import com.messenger.simplemessenger.user.adapter.out.UserAdapter;
import com.messenger.simplemessenger.user.model.UserEntity;
import com.messenger.simplemessenger.user.port.out.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Autowired
    private AuthUserMapper authUserMapper;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        AuthUserEntity user = authUserRepository.findById(username)
                .orElseThrow( () ->
                new UsernameNotFoundException("AuthUser with Username: %s not found.".formatted(username)));


        return authUserMapper.toDomain(user);
    }
}
