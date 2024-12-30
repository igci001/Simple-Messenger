package com.messenger.simplemessenger.chat.mapper;


import com.messenger.simplemessenger.chat.model.dto.Chat;
import com.messenger.simplemessenger.chat.model.ChatEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.ChatRequest;
import org.openapitools.model.ChatResponse;

import java.time.Instant;
import java.time.OffsetDateTime;

@Mapper(componentModel = "spring", uses = {MessageMapper.class, ChatMemberMapper.class})
public interface ChatMapper {

    Chat toDomain(ChatEntity chatEntity);

    @Mapping(target = "owner.enteredChats", ignore = true)
    @Mapping(target = "owner.ownedChats", ignore = true)
    @Mapping(target = "owner.publishedMessages", ignore = true)
    ChatEntity toEntity(Chat chat);

    @Mapping(target = "owner", ignore = true)
    @Mapping(target = "members", ignore = true)
    @Mapping(target = "messages", ignore = true)
    ChatEntity toEntity(ChatRequest chatRequest);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "chatConvertToString")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "chatConvertToString")
    ChatResponse toResponse(Chat chat);


    @Named("chatConvertToString")
    default String convertToString(Instant instant) {
        return instant.toString();
    }
}
