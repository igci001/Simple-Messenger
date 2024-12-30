package com.messenger.simplemessenger.chat.adapter.out;

import com.messenger.simplemessenger.chat.mapper.MessageMapper;
import com.messenger.simplemessenger.chat.port.out.repository.MessageRepository;
import com.messenger.simplemessenger.chat.model.dto.Message;
import com.messenger.simplemessenger.chat.port.out.MessagePort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.MessageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageAdapter implements MessagePort {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Override
    public Message findMessageById(UUID id) {
        return messageMapper.toDomain(
                messageRepository.findById(id)
                        .orElseThrow(
                                () -> new NoSuchElementException("Message with id: %s not found".formatted(id)
                                )
                        )
        );
    }

    @Override
    public Message createMessage(MessageRequest messageRequest,UUID chatId) {
        return messageMapper.toDomain(
          messageRepository.save(messageMapper.toEntity(messageRequest, chatId))
        );
    }

    @Override
    public Message updateMessage(UUID messageId, String message) {

        var messageEntity = messageRepository.
                findById(messageId)
                .orElseThrow(
                        () -> new NoSuchElementException("Message with id: %s not found".formatted(messageId))
                );
        messageEntity.setMessage(message);
        return messageMapper.toDomain(messageRepository.save(messageEntity));
    }

    @Override
    public void deleteMessageById(UUID id) {
        messageRepository.deleteById(id);
    }
}
