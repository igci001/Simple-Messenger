package com.messenger.simplemessenger.chat.port.in.service;

import com.messenger.simplemessenger.chat.model.ChatMemberId;
import org.openapitools.model.ChatMemberRequest;
import org.openapitools.model.ChatMemberResponse;
import org.openapitools.model.UserRole;

import java.util.UUID;

public interface ChatMemberUseCase {

    ChatMemberResponse createChatMember(ChatMemberRequest chatMemberRequest);
    void deleteChatMemberById(UUID chatId, UUID memberId);

    void changeMemberRole(ChatMemberId chatMemberId, UserRole userRole);
}
