package com.messenger.simplemessenger.authentication.model;


import com.messenger.simplemessenger.chat.model.ChatMemberEntity;

import com.messenger.simplemessenger.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;

@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode
@ToString
@Data
@Entity
@Table(name = "auth_users")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthUserEntity {

    @Id
    @NonNull
    @Column(name = "username", columnDefinition = "citext" )
    private String username;

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "password")
    @NonNull
    private String password;

    @OneToOne(mappedBy = "authUser", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private UserEntity userDetails;

}
