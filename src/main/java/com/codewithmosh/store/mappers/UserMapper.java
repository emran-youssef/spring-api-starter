package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.CreateUserRequest;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.dtos.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    //@Mapping(target = "createdAt", expression = "java(java.time.LocalDate.now())")
    UserDto userToUserDto(User user);
    User toEntity(CreateUserRequest request);
}
