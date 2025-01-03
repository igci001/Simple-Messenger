package com.messenger.simplemessenger.authentication.port.out;

import com.messenger.simplemessenger.authentication.model.AuthUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface AuthUserRepository extends JpaRepository<AuthUserEntity, String> {

}
