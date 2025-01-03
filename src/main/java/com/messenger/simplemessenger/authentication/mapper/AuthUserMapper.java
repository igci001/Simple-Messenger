package com.messenger.simplemessenger.authentication.mapper;

import com.messenger.simplemessenger.authentication.model.AuthUserEntity;
import com.messenger.simplemessenger.authentication.model.dto.AuthUser;
import com.messenger.simplemessenger.chat.mapper.ChatMemberMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.openapitools.model.LoginRequest;

@Mapper(componentModel = "spring", uses = {ChatMemberMapper.class})
public interface AuthUserMapper {


    @Mapping(target = "authorities", source = "userDetails.enteredChats")
    AuthUser toDomain(AuthUserEntity authUserEntity);



    AuthUserEntity toEntity(LoginRequest loginRequest);
}
