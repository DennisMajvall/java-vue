package web.app.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
public class Image implements Serializable {

    @Id
    public long auctionId;

    @Id
    public String path;

    public Image() {}
    public Image(Auction owner, String path) {
        this.auctionId = owner.id;
        this.path = path;
    }
}
