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
        if (repo.count() == 0) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "OH NO, there are no posts");
        }
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
