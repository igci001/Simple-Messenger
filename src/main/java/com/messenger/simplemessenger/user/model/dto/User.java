package com.messenger.simplemessenger.user.model.dto;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.UUID;

@Builder
@Data
public class User {
    @NonNull
    private UUID id;

    @Nullable
    private Instant createdDate;

    @Nullable
    private Instant lastModifiedDate;

    @NonNull
    private String surname;

    @NonNull
    private String lastName;

    @NonNull
    private Instant birthday;

    @Nullable
    private String description;
}
