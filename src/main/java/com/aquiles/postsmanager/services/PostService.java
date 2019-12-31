package com.aquiles.postsmanager.services;

import com.aquiles.postsmanager.domain.Post;
import com.aquiles.postsmanager.repository.PostRepository;
import com.aquiles.postsmanager.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        Post post = postRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("ID not found."));
        return post;
    }

    public List<Post> findByTitle(String text){
        List<Post> posts = postRepository.findByTitleContainingIgnoreCase(text);
        return posts;
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        List<Post> posts = postRepository.fullSearch(text, minDate, maxDate);
        return posts;
    }
}
