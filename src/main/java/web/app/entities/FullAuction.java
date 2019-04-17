package web.app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class FullAuction extends Auction{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public List<Image> images = new ArrayList<>();

    public FullAuction() {}
}
