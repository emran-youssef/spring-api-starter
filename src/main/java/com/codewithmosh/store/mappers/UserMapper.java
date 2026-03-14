package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.User.ChangePasswordRequest;
import com.codewithmosh.store.dtos.User.CreateUserRequest;
import com.codewithmosh.store.dtos.User.UpdateUserRequest;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.dtos.User.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDto userToUserDto(User user);

    User toEntity(CreateUserRequest request);

    void update(UpdateUserRequest request, @MappingTarget User user);
    // MapStruct doesn't create new user object it just updates the EXISTING user object

    void changePassword(ChangePasswordRequest request, @MappingTarget User user);
}
