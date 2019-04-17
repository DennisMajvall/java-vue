package web.app.entities;

import web.app.entities.User;
import javax.persistence.*;

@Entity
public class Auction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public long id;

    public String title;
    public String description;
    public int startingBid;

    @ManyToOne
    public User seller;

    public Auction() {}
}
