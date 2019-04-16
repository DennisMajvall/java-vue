package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.app.entities.Post;
import web.app.entities.User;
import web.app.repositories.PostRepository;
import web.app.services.SocketService;

import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
@RequestMapping("/api/posts/")
public class PostController {

    @Autowired
    SocketService socketService;

    @Autowired
    PostRepository repo;

    @GetMapping
    Iterable getPosts(Principal principal){
        // For testing purpose only
        System.out.println("Is the user doing the request an admin? - " + User.currentUserIsAdmin(principal));

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
        // code...
    }
}
