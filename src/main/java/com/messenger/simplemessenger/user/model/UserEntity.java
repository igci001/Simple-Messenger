package com.messenger.simplemessenger.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messenger.simplemessenger.chat.model.ChatMemberEntity;
import com.messenger.simplemessenger.core.BaseEntity;
import com.messenger.simplemessenger.chat.model.ChatEntity;
import com.messenger.simplemessenger.chat.model.MessageEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;
import org.springframework.lang.Nullable;

import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
public class UserEntity extends BaseEntity {

    @NonNull
    @Column(name = "surname")
    private String surname;

    @NonNull
    @Column(name = "last_name")
    private String lastName;

    @NonNull
    @Column(name = "birth_day")
    private Instant birthday;

    @Nullable
    @Column(name = "description")
    private String description;

    // relations

    @OneToMany(mappedBy = "user")
    @JsonIgnore
    @ToString.Exclude
    private List<ChatMemberEntity> enteredChats;

    @OneToMany(mappedBy = "owner")
    @JsonIgnore
    @ToString.Exclude
    private List<ChatEntity> ownedChats;

    @OneToMany(mappedBy = "author")
    @JsonIgnore
    @ToString.Exclude
    private List<MessageEntity> publishedMessages;
}
