package web.app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
public class FullAuction extends Auction{

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Image> images = new ArrayList<>();

    public FullAuction() {}

    public List<String> getImages(){
        return images.stream().map(x -> x.path).collect(Collectors.toList());
    }

    public void addImage(String path){
        this.images.add(new Image(path));
    }
}
