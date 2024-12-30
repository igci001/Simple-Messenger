package com.messenger.simplemessenger.chat.model.dto;

import com.messenger.simplemessenger.user.model.dto.User;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class Message {
    @NonNull
    private UUID id;

    @Nullable
    private Instant createdDate;

    @Nullable
    private Instant lastModifiedDate;

    @NonNull
    private String message;

    @NonNull
    private UUID chatId;

    @NonNull
    private User author;
}
