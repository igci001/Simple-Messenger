package com.messenger.simplemessenger.chat.port.out.repository;

import com.messenger.simplemessenger.chat.model.MessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface MessageRepository extends JpaRepository<MessageEntity, UUID> {
}
