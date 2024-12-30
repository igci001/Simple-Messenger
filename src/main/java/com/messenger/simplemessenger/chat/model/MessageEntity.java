package com.messenger.simplemessenger.chat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messenger.simplemessenger.chat.model.ChatEntity;
import com.messenger.simplemessenger.core.BaseEntity;
import com.messenger.simplemessenger.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "message")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class MessageEntity extends BaseEntity {

    @NonNull
    @Column(name = "message")
    private String message;

    // relations

    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    @JsonIgnore
    private ChatEntity chat;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private UserEntity author;
}
