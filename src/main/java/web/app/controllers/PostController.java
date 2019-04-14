package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import web.app.entities.Post;
import web.app.entities.User;
import web.app.repositories.PostRepository;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    PostRepository repo;

    @GetMapping
    Iterable getPosts(Principal principal){
        User currentUser = (User) ((Authentication) principal).getPrincipal();
        boolean isAdmin = currentUser.isAdmin();
        return repo.findAll();
    }

    @PostMapping
    void addPost(@RequestBody Post body) {
        repo.save(body);
    }

    @DeleteMapping
    @RolesAllowed("ADMIN")
    void deletePost(){
        System.out.println("deleting post...");
    }
}
