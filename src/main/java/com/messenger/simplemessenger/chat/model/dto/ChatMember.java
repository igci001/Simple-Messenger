package com.messenger.simplemessenger.chat.model.dto;

import com.messenger.simplemessenger.user.model.dto.User;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.openapitools.model.UserRole;
import org.springframework.security.core.GrantedAuthority;

import java.time.Instant;


@Builder
@Data
public class ChatMember implements GrantedAuthority {

    @NonNull
    private Instant createdDate;

    @NonNull
    private Instant lastModifiedDate;

    @NonNull
    private final User user;

    @NonNull
    private final Chat chat;

    @NonNull
    private UserRole userRole;

    @Override
    public String getAuthority() {
        return userRole.toString();
    }
}
