package com.aquiles.postsmanager.resources;

import com.aquiles.postsmanager.domain.Post;
import com.aquiles.postsmanager.resources.util.URL;
import com.aquiles.postsmanager.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService postService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id) {
        Post post = postService.findById(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping(value = "/titleSearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text", defaultValue = "") String text) {
        String urlParam = URL.decodeParam(text);
        List<Post> post = postService.findByTitle(urlParam);
        return ResponseEntity.ok().body(post);
    }
}
