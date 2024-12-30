package com.messenger.simplemessenger.chat.port.out;

import com.messenger.simplemessenger.chat.model.dto.Chat;
import org.openapitools.model.ChatRequest;

import java.util.UUID;

public interface ChatPort {

    Chat findChatById(UUID id);

    Chat createChat(ChatRequest chatRequest);

    Chat updateChat(ChatRequest chatRequest);

    void deleteChatById(UUID id);
}
