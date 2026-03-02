package com.codewithmosh.store.controllers;

import com.codewithmosh.store.dtos.UserDto;
import com.codewithmosh.store.mappers.ProductMapper;
import com.codewithmosh.store.mappers.UserMapper;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import com.codewithmosh.store.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@AllArgsConstructor
@RestController
@RequestMapping("/users")
public class HomeController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private ProductRepository productRepository;
    private ProductMapper productMapper;
    private CategoryRepository categoryRepository;



    @GetMapping()
    public Iterable<UserDto> getAllUsers(
          @RequestParam(name = "sort", required = false, defaultValue = "") String sortBy
    ){

        if(!Set.of("name", "email").contains(sortBy)){
            sortBy = "id";
        }
       return userRepository.findAll(Sort.by(sortBy))
               .stream()
               .map(userMapper::userToUserDto)
               .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity <UserDto> getUser(@PathVariable Long id){
        var user = userRepository.findById(id).orElse(null);

        if(user == null){
            return ResponseEntity.notFound().build();
        }
           //var userDto = new UserDto(user.getId(), user.getName(), user.getEmail());
           //var userDto = userMapper.userToUserDto(user);
           return ResponseEntity.ok(userMapper.userToUserDto(user));
    }


}
