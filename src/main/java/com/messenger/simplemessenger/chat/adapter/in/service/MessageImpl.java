package com.messenger.simplemessenger.chat.adapter.in.service;

import com.messenger.simplemessenger.chat.mapper.MessageMapper;
import com.messenger.simplemessenger.chat.port.in.service.MessageUseCase;
import com.messenger.simplemessenger.chat.port.out.MessagePort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.MessageRequest;
import org.openapitools.model.MessageResponse;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class MessageImpl implements MessageUseCase {

    private final MessagePort messagePort;
    private final MessageMapper messageMapper;

    @Override
    public MessageResponse findMessageById(UUID id) {
        return messageMapper.toResponse(messagePort.findMessageById(id));
    }

    @Override
    public MessageResponse createMessage(MessageRequest messageRequest, UUID chatId) {
        return messageMapper.toResponse(messagePort.createMessage(messageRequest, chatId));
    }

    @Override
    public MessageResponse updateMessage(UUID messageId, String message) {
        return messageMapper.toResponse(messagePort.updateMessage(messageId, message));
    }

    @Override
    public void deleteMessageById(UUID id) {
        messagePort.deleteMessageById(id);
    }
}
