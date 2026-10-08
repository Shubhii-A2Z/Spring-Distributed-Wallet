package com.example.shardedsagawallet.services;

import org.springframework.stereotype.Service;

import com.example.shardedsagawallet.models.User;
import com.example.shardedsagawallet.repositories.UserRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;


@Service
@AllArgsConstructor
@Slf4j

public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @Override
    public User createUser(User user){
        User newUser=userRepository.save(user);
        log.info("User created with id {} in database shardwallet{}", newUser.getId(), (newUser.getId()%2+1));
        return newUser;
    }

    @Override
    public User getById(Long id){
        User user=userRepository.findById(id).orElse(null);
        return user;
    }

}
