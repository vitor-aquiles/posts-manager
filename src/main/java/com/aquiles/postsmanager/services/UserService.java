package com.aquiles.postsmanager.services;

import com.aquiles.postsmanager.domain.Post;
import com.aquiles.postsmanager.domain.User;
import com.aquiles.postsmanager.dto.UserDTO;
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

    public User insert(User user){
        return userRepository.insert(user);
    }

    public User update(User userRequest){
        User user = findById(userRequest.getId());
        updateData(userRequest, user);
        return userRepository.save(user);
    }

    public void delete(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public User fromDTO(UserDTO userDTO){
        User user = new User(userDTO.getId(), userDTO.getName(), userDTO.getEmail());
        return user;
    }

    public void updateData(User userRequest, User user){
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
    }

    public List<Post> getPostsByUserId(String id) {
        User user = findById(id);
        return user.getPosts();
    }
}
