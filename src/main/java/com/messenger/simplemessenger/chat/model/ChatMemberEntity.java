package com.messenger.simplemessenger.chat.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messenger.simplemessenger.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.openapitools.model.UserRole;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Table(name = "chat_member")
@NoArgsConstructor
@AllArgsConstructor
@Builder()
@EqualsAndHashCode
@EntityListeners(AuditingEntityListener.class)
@IdClass(ChatMemberId.class)
@Data
public class ChatMemberEntity implements Serializable {

    @CreatedDate
    @Column(name = "created_date", updatable = false)
    private Instant createdDate;

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Instant lastModifiedDate;

    @Column(name = "user_role")
    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    // relations

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id",nullable = false)
    @JsonIgnore
    private UserEntity user;

    @Id
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private ChatEntity chat;
}
