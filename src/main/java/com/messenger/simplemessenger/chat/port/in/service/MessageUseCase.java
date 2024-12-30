package com.messenger.simplemessenger.chat.port.in.service;

import com.messenger.simplemessenger.chat.model.dto.Message;
import org.openapitools.model.MessageRequest;
import org.openapitools.model.MessageResponse;

import java.util.UUID;


public interface MessageUseCase {

    MessageResponse findMessageById(UUID id);
    MessageResponse createMessage(MessageRequest messageRequest, UUID chatId);
    MessageResponse updateMessage(UUID messageId, String message);

    void deleteMessageById(UUID id);
}
