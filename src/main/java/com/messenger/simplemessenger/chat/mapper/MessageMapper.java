package com.messenger.simplemessenger.chat.mapper;

import com.messenger.simplemessenger.chat.port.out.repository.ChatRepository;
import com.messenger.simplemessenger.chat.model.dto.Message;
import com.messenger.simplemessenger.chat.model.ChatEntity;
import com.messenger.simplemessenger.chat.model.MessageEntity;
import com.messenger.simplemessenger.user.model.UserEntity;
import com.messenger.simplemessenger.user.port.out.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.MessageRequest;
import org.openapitools.model.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.NoSuchElementException;
import java.util.UUID;

@Mapper(componentModel = "spring")
public abstract class MessageMapper {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ChatRepository chatRepository;

    @Mapping(target = "chatId", source = "messageEntity.chat.id")
    @Mapping(target = "author.enteredChats", ignore = true)
    public abstract Message toDomain(MessageEntity messageEntity);
    public MessageEntity toEntity(Message message) {
        UserEntity user = userRepository.findById(message.getAuthor().getId()).orElseThrow(
                () -> new NoSuchElementException("User with Id: %s does not exists".formatted(message.getAuthor().getId()))
        );

        ChatEntity chat = chatRepository.findById(message.getChatId()).orElseThrow(
                () -> new NoSuchElementException("Chat with Id: %s does not exists".formatted(message.getChatId()))
        );

        MessageEntity messageEntity = new MessageEntity();
        messageEntity.setMessage(message.getMessage());
        messageEntity.setAuthor(user);
        messageEntity.setChat(chat);


        return messageEntity;
    }
    public MessageEntity toEntity(MessageRequest messageRequest, UUID chatId){
        UserEntity user = userRepository.findById(messageRequest.getAuthor()).orElseThrow(
                () -> new NoSuchElementException("User with Id: %s does not exists".formatted(messageRequest.getAuthor()))
        );

        ChatEntity chat = chatRepository.findById(chatId).orElseThrow(
                () -> new NoSuchElementException("Chat with Id: %s does not exists".formatted(chatId))
        );

        MessageEntity message = new MessageEntity();
        message.setMessage(messageRequest.getMessage());
        message.setAuthor(user);
        message.setChat(chat);


        return message;
    };

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "messageConvertToString")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "messageConvertToString")
    public abstract MessageResponse toResponse(Message message);

    @Named("messageConvertToString")
    protected String convertToString(Instant instant) {
        return instant.toString();
    }
}
