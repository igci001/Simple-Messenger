package com.messenger.simplemessenger.chat.port.in.service;

import org.openapitools.model.ChatRequest;
import org.openapitools.model.ChatResponse;

import java.util.UUID;

public interface ChatUseCase {

    ChatResponse findChatById(UUID id);

    ChatResponse createChat(ChatRequest chatRequest);

    ChatResponse updateChat(ChatRequest chatRequest);

    void deleteChatById(UUID id);
}
