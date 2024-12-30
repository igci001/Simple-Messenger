package com.messenger.simplemessenger.chat.port.out.repository;

import com.messenger.simplemessenger.chat.model.ChatMemberEntity;
import com.messenger.simplemessenger.chat.model.ChatMemberId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatMemberRepository extends JpaRepository<ChatMemberEntity, ChatMemberId> {
}
