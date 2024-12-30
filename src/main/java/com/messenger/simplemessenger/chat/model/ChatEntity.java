package com.messenger.simplemessenger.chat.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.messenger.simplemessenger.core.BaseEntity;
import com.messenger.simplemessenger.user.model.UserEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "chat")
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@NoArgsConstructor
public class ChatEntity extends BaseEntity {
    @NonNull
    @Column(name = "name")
    private String name;
    @NonNull
    @Column(name = "description")
    private String description;

    // relations
    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    @JsonIgnore
    private UserEntity owner;

    @OneToMany(mappedBy = "chat", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JsonIgnore
    private List<ChatMemberEntity> members;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<MessageEntity> messages;
}
