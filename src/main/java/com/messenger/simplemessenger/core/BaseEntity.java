package com.messenger.simplemessenger.core;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;


@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@ToString
@Getter
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    @NonNull
    private UUID id;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    @NonNull
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    @NonNull
    private Instant lastModifiedDate;
}
