package com.messenger.simplemessenger.chat.port.out;

import com.messenger.simplemessenger.chat.model.dto.ChatMember;
import com.messenger.simplemessenger.chat.model.ChatMemberId;
import org.openapitools.model.ChatMemberRequest;
import org.openapitools.model.UserRole;

public interface ChatMemberPort {

    ChatMember createChatMember(ChatMemberRequest chatMemberRequest);
    void deleteChatMemberById(ChatMemberId id);

    void changeMemberRole(ChatMemberId chatMemberId, UserRole userRole);
}
