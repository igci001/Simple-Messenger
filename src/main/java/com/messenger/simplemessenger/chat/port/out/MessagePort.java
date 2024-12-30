package com.messenger.simplemessenger.chat.port.out;

import com.messenger.simplemessenger.chat.model.dto.Message;
import org.openapitools.model.MessageRequest;

import java.util.UUID;

public interface MessagePort {
    Message findMessageById(UUID id);
    Message createMessage(MessageRequest messageRequest, UUID chatId);
    Message updateMessage(UUID messageId, String message);

    void deleteMessageById(UUID id);
}
