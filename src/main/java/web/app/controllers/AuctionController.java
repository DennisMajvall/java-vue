package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.app.entities.*;
import web.app.repositories.FullAuctionRepository;
import web.app.repositories.PostRepository;
import web.app.repositories.UserRepository;
import web.app.services.SocketService;

import javax.annotation.PostConstruct;
import javax.annotation.security.RolesAllowed;
import java.security.Principal;

@RestController
@RequestMapping("/api/auctions/")
public class AuctionController {

    @Autowired
    FullAuctionRepository repo;

    @Autowired
    UserRepository userRepo;

    @PostConstruct
    void init(){
        FullAuction fa = new FullAuction();
        fa.title = "The Name of the Wind";
        fa.description = "A heroic fantasy novel written by Patrick Rothfuss.";
        fa.seller = userRepo.findById(1L).get();
        fa.startingBid = 200;

        fa.images.add(new Image(fa, "/cool-image.jpg"));
        fa.images.add(new Image(fa, "/cool-image-2.jpg"));

        repo.save(fa);
    }

    @GetMapping
    Iterable get(){
        return repo.findAll();
    }

    @PostMapping
    void post(@RequestBody FullAuction body) {
        repo.save(body);
    }

    @DeleteMapping
    @RolesAllowed("ADMIN")
    void delete(){
        System.out.println("deleting FullAuction...");
        // code...
    }
}
