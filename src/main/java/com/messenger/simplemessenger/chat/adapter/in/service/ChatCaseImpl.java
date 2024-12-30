package com.messenger.simplemessenger.chat.adapter.in.service;

import com.messenger.simplemessenger.chat.mapper.ChatMapper;
import com.messenger.simplemessenger.chat.port.in.service.ChatUseCase;
import com.messenger.simplemessenger.chat.port.out.ChatPort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.ChatRequest;
import org.openapitools.model.ChatResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatCaseImpl implements ChatUseCase {

    private final ChatPort chatPort;
    private final ChatMapper chatMapper;


    @Override
    public ChatResponse findChatById(UUID id) {
        return chatMapper.toResponse(chatPort.findChatById(id));
    }

    @Override
    public ChatResponse createChat(ChatRequest chatRequest) {
        return chatMapper.toResponse(chatPort.createChat(chatRequest));
    }

    @Override
    public ChatResponse updateChat(ChatRequest chatRequest) {
        return chatMapper.toResponse(chatPort.updateChat(chatRequest));
    }

    @Override
    public void deleteChatById(UUID id) {
        chatPort.deleteChatById(id);
    }
}
