package com.aquiles.postsmanager.resources;

import com.aquiles.postsmanager.domain.Post;
import com.aquiles.postsmanager.resources.util.URL;
import com.aquiles.postsmanager.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
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
        List<Post> posts = postService.findByTitle(urlParam);
        return ResponseEntity.ok().body(posts);
    }

    @GetMapping(value = "/fullSearch")
    public ResponseEntity<List<Post>> fullSearch(
            @RequestParam(value = "text", defaultValue = "") String text,
            @RequestParam(value = "minDate", defaultValue = "") String minDate,
            @RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
        String urlParam = URL.decodeParam(text);
        Date minDateConverted = URL.convertDateParam(minDate, new Date(0L));
        Date maxDateConverted = URL.convertDateParam(maxDate, new Date());
        List<Post> posts = postService.fullSearch(text, minDateConverted, maxDateConverted);
        return ResponseEntity.ok().body(posts);
    }
}
