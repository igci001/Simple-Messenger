package com.messenger.simplemessenger.authentication.model.dto;

import com.messenger.simplemessenger.chat.model.dto.ChatMember;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.util.Collection;
import java.util.List;

@Data
@Builder
public class AuthUser implements UserDetails {



    private Instant createdDate;

    private Instant lastModifiedDate;

    @NonNull
    private String username;

    @NonNull
    private String password;

    private List<ChatMember> authorities;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
