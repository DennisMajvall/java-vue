package web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    PostRepository repo;

    @Autowired
    FileManager fileManager;

    @GetMapping
    Iterable getPosts(){
        return repo.findAll();
    }

    @GetMapping("/make")
    void makePost(){
        fileManager.createAnEmptyFile("random name");
    }

    @PostMapping
    void addPost(@RequestBody Post body) {
        repo.save(body);
    }
}
