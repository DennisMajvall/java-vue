package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.app.entities.Post;
import web.app.repositories.PostRepository;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    PostRepository repo;

    @GetMapping
    Iterable getPosts(){
        return repo.findAll();
    }

    @PostMapping
    void addPost(@RequestBody Post body) {
        repo.save(body);
    }
}
