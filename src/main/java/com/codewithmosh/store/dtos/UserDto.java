package com.codewithmosh.store.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Setter
@Getter
public class UserDto {

    private Long id;
    private String name;
    private String email;

   // private LocalDate createdAt; //this field doesn't exist in DB, so we must mapp it in the interface
}
