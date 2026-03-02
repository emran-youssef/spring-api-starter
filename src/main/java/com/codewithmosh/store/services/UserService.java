package com.codewithmosh.store.services;


import com.codewithmosh.store.entities.Category;
import com.codewithmosh.store.entities.Product;
import com.codewithmosh.store.entities.User;
import com.codewithmosh.store.repositories.CategoryRepository;
import com.codewithmosh.store.repositories.ProductRepository;
import com.codewithmosh.store.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository CategoryRepository;



    public void addUser(){
       var user =  User.builder()
                .name("Ali Mohammad")
                .email("ali@gmail.com")
                .password("0000").build();
        userRepository.save(user);
    }


    @Transactional
    public void addProduct(){
        var category = CategoryRepository.findById((byte) 2).orElseThrow();

        var product = Product.builder()
                .name("Sofa Chair")
                .category(category)
                .description("Comfortable and stylish sofa chair, Black color with a Audio Speaker")
                .price(BigDecimal.valueOf(299.99)).build();

        productRepository.save(product);
    }



}

