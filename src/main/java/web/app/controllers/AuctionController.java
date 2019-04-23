package web.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import web.app.entities.FullAuction;
import web.app.entities.User;
import web.app.repositories.FullAuctionRepository;
import web.app.repositories.UserRepository;

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

        fa.addImage("/cool-image.jpg");
        fa.addImage("/cool-image-2.jpg");

        repo.save(fa);
    }

    @GetMapping
    Iterable get(){
        return repo.findAll();
    }

    @PostMapping
    void post(@RequestBody FullAuction auction, Principal principal) {
        User uploader = userRepo.findDistinctFirstByUsernameIgnoreCase(principal.getName());
        auction.seller = uploader;

        repo.save(auction);
    }

    @DeleteMapping
    @RolesAllowed("ADMIN")
    void delete(){
        System.out.println("deleting FullAuction...");
        // code...
    }
}
