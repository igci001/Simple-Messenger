package com.messenger.simplemessenger.user;

import com.messenger.simplemessenger.user.model.UserEntity;
import com.messenger.simplemessenger.user.model.dto.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.openapitools.model.UserRequest;
import org.openapitools.model.UserResponse;

import java.time.Instant;


@Mapper(componentModel = "spring")
public interface UserMapper {

    User toDomain(UserEntity userEntity);
    UserEntity toEntity(User user);

    @Mapping(target = "birthday", source = "birthday", qualifiedByName = "convertToInstant")
    UserEntity toEntity(UserRequest userRequest);

    @Mapping(target = "createdDate", source = "createdDate", qualifiedByName = "convertToString")
    @Mapping(target = "lastModifiedDate", source = "lastModifiedDate", qualifiedByName = "convertToString")
    UserResponse toResponse(User user);

    @Named("convertToInstant")
    default Instant convertToInstant(String string) {
        return Instant.parse(string);
    }

    @Named("convertToString")
    default String convertToString(Instant instant) {
        return instant.toString();
    }

}
