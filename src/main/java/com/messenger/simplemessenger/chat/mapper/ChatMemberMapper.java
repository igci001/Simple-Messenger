package com.messenger.simplemessenger.chat.mapper;

import com.messenger.simplemessenger.chat.port.out.repository.ChatRepository;
import com.messenger.simplemessenger.chat.model.ChatMemberEntity;
import com.messenger.simplemessenger.chat.model.dto.ChatMember;
import com.messenger.simplemessenger.user.UserMapper;
import com.messenger.simplemessenger.user.port.out.UserRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.ChatMemberRequest;
import org.openapitools.model.ChatMemberResponse;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Instant;
import java.util.NoSuchElementException;

@Mapper(componentModel = "spring", uses = {MessageMapper.class})
public abstract class ChatMemberMapper {

    @Autowired
    private ChatRepository chatRepository;
    @Autowired
    private UserRepository userRepository;


    @Mapping(target = "chat.members", ignore = true)
    @Mapping(target = "chat.messages", ignore = true)
    @Mapping(target = "user.enteredChats", ignore = true)
    @Mapping(target = "chat.owner.enteredChats", ignore = true)
    public abstract ChatMember toDomain(ChatMemberEntity chatMemberEntity);
    public  ChatMemberEntity toEntity(ChatMember chatMember){
        var member = userRepository
                .findById(chatMember.getUser().getId())
                .orElseThrow(()-> new NoSuchElementException("User with Id: %s not found".formatted(chatMember.getUser().getId())));
        var chat = chatRepository
                .findById(chatMember.getChat().getId())
                .orElseThrow(()-> new NoSuchElementException("Chat with Id: %s not found".formatted(chatMember.getChat().getId())));

        ChatMemberEntity chatMemberEntity = new ChatMemberEntity();
        chatMemberEntity.setChat(chat);
        chatMemberEntity.setUser(member);
        chatMemberEntity.setUserRole(chatMember.getUserRole());

        return chatMemberEntity;
    }

    public ChatMemberEntity toEntity(ChatMemberRequest chatMemberRequest){
        var member = userRepository
                .findById(chatMemberRequest.getUserId())
                .orElseThrow(()-> new NoSuchElementException("User with Id: %s not found".formatted(chatMemberRequest.getUserId())));
        var chat = chatRepository
                .findById(chatMemberRequest.getChatId())
                .orElseThrow(()-> new NoSuchElementException("Chat with Id: %s not found".formatted(chatMemberRequest.getChatId())));

        ChatMemberEntity chatMemberEntity = new ChatMemberEntity();
        chatMemberEntity.setChat(chat);
        chatMemberEntity.setUser(member);
        chatMemberEntity.setUserRole(chatMemberRequest.getUserRole());

        return chatMemberEntity;
    }
    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "chatMemberConvertToString")
    @Mapping(target = "lastModifiedDate", source = "createdDate", qualifiedByName = "chatMemberConvertToString")
    public abstract ChatMemberResponse toResponse(ChatMember chatMember);

    @Named("chatMemberConvertToString")
    protected String convertToString(Instant instant) {
        return instant.toString();
    }
}
