package com.messenger.simplemessenger.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messenger.simplemessenger.authentication.model.AuthUserEntity;
import com.messenger.simplemessenger.chat.model.ChatMemberEntity;
import com.messenger.simplemessenger.core.BaseEntity;
import com.messenger.simplemessenger.chat.model.ChatEntity;
import com.messenger.simplemessenger.chat.model.MessageEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.lang.Nullable;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "\"user\"")
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class UserEntity extends BaseEntity {

    @NonNull
    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

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

    @OneToOne()
    @JoinColumn(name = "auth_user_id")
    @NotNull
    @ToString.Exclude
    private AuthUserEntity authUser;

}
