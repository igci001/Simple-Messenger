package com.messenger.simplemessenger.chat.port.out.repository;

import com.messenger.simplemessenger.chat.model.ChatEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ChatRepository extends JpaRepository<ChatEntity, UUID> {

}
