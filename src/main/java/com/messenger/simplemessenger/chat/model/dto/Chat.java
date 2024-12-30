package com.messenger.simplemessenger.chat.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messenger.simplemessenger.user.model.dto.User;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.List;
import java.util.UUID;


@Builder
@Data
public class Chat {

    @NonNull
    private UUID id;

    @Nullable
    private Instant createdDate;

    @Nullable
    private Instant lastModifiedDate;

    @NonNull
    private String name;
    @Nullable
    private String description;

    @NonNull
    private User owner;

    @Nullable
    private List<ChatMember> members;

    @Nullable
    private List<Message> messages;
}
