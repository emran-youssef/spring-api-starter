package com.codewithmosh.store.dtos.User;

import lombok.Data;

@Data
public class UpdateUserRequest {
    private String name;
    private String email;
}
