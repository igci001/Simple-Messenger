package com.messenger.simplemessenger.chat.adapter.in.service;

import com.messenger.simplemessenger.chat.mapper.ChatMemberMapper;
import com.messenger.simplemessenger.chat.model.ChatMemberId;
import com.messenger.simplemessenger.chat.port.in.service.ChatMemberUseCase;
import com.messenger.simplemessenger.chat.port.out.ChatMemberPort;
import lombok.RequiredArgsConstructor;
import org.openapitools.model.ChatMemberRequest;
import org.openapitools.model.ChatMemberResponse;
import org.openapitools.model.UserRole;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class ChatMemberImpl implements ChatMemberUseCase {

    private final ChatMemberPort chatMemberPort;
    private final ChatMemberMapper chatMemberMapper;

    @Override
    public ChatMemberResponse createChatMember(ChatMemberRequest chatMemberRequest) {
        return chatMemberMapper.toResponse(chatMemberPort.createChatMember(chatMemberRequest));
    }

    @Override
    public void deleteChatMemberById(UUID chatId, UUID memberId) {
        chatMemberPort.deleteChatMemberById(new ChatMemberId(chatId,memberId));
    }

    @Override
    public void changeMemberRole(ChatMemberId chatMemberId, UserRole userRole) {
        chatMemberPort.changeMemberRole(chatMemberId, userRole);
    }
}
