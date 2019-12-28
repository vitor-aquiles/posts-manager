package com.aquiles.postsmanager.services;

import com.aquiles.postsmanager.domain.User;
import com.aquiles.postsmanager.repository.UserRepository;
import com.aquiles.postsmanager.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll(){
        return userRepository.findAll();
    }

    public User findById(String id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID not found."));
        return user;
    }
}
