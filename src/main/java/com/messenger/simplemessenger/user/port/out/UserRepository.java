package com.messenger.simplemessenger.user.port.out;

import com.messenger.simplemessenger.user.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<UserEntity, UUID> {
}
