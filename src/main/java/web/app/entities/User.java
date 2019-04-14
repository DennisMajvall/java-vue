package web.app.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

    @ElementCollection
    private Collection<String> roles = new ArrayList<String>();

    public User() {}

    public User(String username, String password) {
        this(username, password, null);
    }

    public User(String username, String password, List<String> roles) {
        this.username = username;
        this.password = password;
        if (roles == null) {
            roles = List.of("USER");
        }
        this.roles = roles;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String[] getRoles() {
        System.out.println("ROLES");
        System.out.println(roles);
        System.out.println("ROLES END");

        return roles.toArray(String[]::new);
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public boolean isAdmin(){
        return roles.contains("ADMIN");
    }
}