package web.app.entities;

import javax.persistence.*;

@Entity
public class UserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    public String role;

    public UserRole() {}
    public UserRole(String role) {
        this.role = role;
    }
    public UserRole(String role, User user) {
        this.role = role;
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        UserRole ur = (UserRole) obj;
        System.out.println("What is ur: " +  ur);
        if (ur != null) {
            return ur.role.equals(this.role);
        } else {
            return hashCode() == obj.hashCode();
        }
    }
}
