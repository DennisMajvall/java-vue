package web.app.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Image {

    @Id
    public String path;

    public Image() {}
    public Image(String path) {
        this.path = path;
    }
}
