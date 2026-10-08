package com.example.shardedsagawallet.services;

import com.example.shardedsagawallet.models.User;

public interface UserService {
    
    User createUser(User user);
    User getById(Long id);

}
