package com.messenger.simplemessenger.chat.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;


@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode
public class ChatMemberId implements Serializable {
    private UUID chat;
    private UUID user;

}
