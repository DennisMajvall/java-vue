package web.security;

import grp.gapi.db.User;
import grp.gapi.db.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class MyUserDetailsService implements UserDetailsService {
    private static List<User> users = new ArrayList();
    public static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repository;

    @PostConstruct
    private void createDefaultUsers(){
        if (repository.findDistinctFirstByNameIgnoreCase("erin") == null) {
            addUser("erin", "123");
        }
        users.addAll(repository.findAll());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = repository.findByUsername(username);
        users.stream()
                .filter(u -> u.name.equals(username))
                .findAny();
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user.get());
    }

    public void addUser(String name, String password){
        User u = new User(name, encoder.encode(password));
        try {
            repository.save(u);
            users.add(u);
        } catch (Exception ex) {
            System.out.println("error: " + ex);
        }
    }

    private UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User.withUsername(user.getName())
                .password(user.getPassword()).roles("USER").build();
    }
}
