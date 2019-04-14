package web.app.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import web.app.entities.User;
import web.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@Configuration
public class MyUserDetailsService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public BCryptPasswordEncoder getEncoder() { return encoder; }

    @Autowired
    private UserRepository repository;


    @PostConstruct
    private void createDefaultUsers(){
        if (repository.findDistinctFirstByUsernameIgnoreCase("user") == null) {
            addUser("user", "password");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repository.findDistinctFirstByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user);
    }

    public void addUser(String name, String password){
        User u = new User(name, encoder.encode(password));
        try {
            repository.save(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private UserDetails toUserDetails(User user) {
        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles("USER").build();
    }
}
