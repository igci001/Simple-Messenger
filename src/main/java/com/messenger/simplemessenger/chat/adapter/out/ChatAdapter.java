package com.messenger.simplemessenger.chat.adapter.out;

import com.messenger.simplemessenger.chat.mapper.ChatMapper;
import com.messenger.simplemessenger.chat.model.ChatEntity;
import com.messenger.simplemessenger.chat.model.ChatMemberEntity;
import com.messenger.simplemessenger.chat.port.out.ChatPort;
import com.messenger.simplemessenger.chat.port.out.repository.ChatRepository;
import com.messenger.simplemessenger.chat.model.dto.Chat;
import com.messenger.simplemessenger.user.port.out.UserRepository;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.ChatRequest;
import org.openapitools.model.UserRole;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class ChatAdapter implements ChatPort {

    private final ChatMapper chatMapper;
    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ChatMemberAdapter chatMemberAdapter;



    @Override
    public Chat findChatById(UUID id) {
        return chatMapper.toDomain(
                chatRepository
                        .findById(id)
                        .orElseThrow(()-> new NoSuchElementException("Chat with id: %s not found".formatted(id))
                        )

        );
    }

    @Override
    public Chat createChat(ChatRequest chatRequest) {

        var user = userRepository
                .findById(chatRequest.getOwner())
                .orElseThrow(
                        () -> new NoSuchElementException("User with Id: %s not exists".formatted(chatRequest.getOwner()))
                );

        ChatEntity chat = chatMapper.toEntity(chatRequest);

        ChatMemberEntity ownerMember = ChatMemberEntity.builder()
                .chat(chat)
                .user(user)
                .userRole(UserRole.OWNER)
                .build();

        chat.setOwner(user);
        chat.setMembers(List.of(ownerMember));

        return chatMapper.toDomain(chatRepository.save(chat));
    }

    @Override
    public Chat updateChat(ChatRequest chatRequest) {
        if(chatRequest.getId() == null)
            throw new IllegalArgumentException("Id cant be null");

        if(!chatRepository.existsById(chatRequest.getId()))
            throw new NoSuchElementException("User with id: %s not found".formatted(chatRequest.getId()));

        var user = userRepository
                .findById(chatRequest.getOwner())
                .orElseThrow(
                        () -> new NoSuchElementException("User with Id: %s not exists".formatted(chatRequest.getOwner()))
                );

        var chat = chatMapper.toEntity(chatRequest);

        chat.setOwner(user);

        return chatMapper.toDomain(chatRepository.save(chat));
    }

    @Override
    public void deleteChatById(UUID id) {
        chatRepository.deleteById(id);
    }
}
