package web.app.entities;

import org.springframework.security.core.Authentication;

import javax.persistence.*;
import java.security.Principal;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Entity
@Table(name="user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade={CascadeType.ALL})
    @JoinColumn(name="user_id")
    private Set<UserRole> roles;

    public User() {}

    public User(String username, String password, String ...roles) {
        this.username = username;
        this.password = password;

        if (roles.length == 0) {
            roles = new String[]{"USER"};
        }

        this.roles = Stream.of(roles).map(s -> new UserRole(s, this)).collect(Collectors.toSet());
    }

    // Helper function used by RestControllers
    public static boolean currentUserIsAdmin(Principal principal){
        org.springframework.security.core.userdetails.User u = (org.springframework.security.core.userdetails.User) ((Authentication) principal).getPrincipal();
        return u.getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("ROLE_ADMIN"));
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

    public Set<UserRole> getRoles() {
        return roles;
    }

    public void setRoles(Set<UserRole> roles) {
        this.roles = roles;
    }

    public boolean isAdmin(){
        return roles.contains("ADMIN");
    }
}