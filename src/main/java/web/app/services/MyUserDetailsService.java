package web.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import web.app.entities.User;
import web.app.repositories.UserRepository;

import javax.annotation.PostConstruct;

@Service
public class MyUserDetailsService implements UserDetailsService {

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    public BCryptPasswordEncoder getEncoder() { return encoder; }

    @Autowired
    private UserRepository userRepository;

    @PostConstruct
    private void createDefaultUsers(){
        if (userRepository.findDistinctFirstByUsernameIgnoreCase("root") == null) {
            addUser("user", "password","USER");
            addUser("root", "password", "ADMIN", "USER");
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findDistinctFirstByUsernameIgnoreCase(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found by name: " + username);
        }
        return toUserDetails(user);
    }

    public void addUser(String username, String password, String ...roles){
        User u = new User(username, encoder.encode(password), roles);
        try {
            userRepository.save(u);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private UserDetails toUserDetails(User user) {
        String[] roleArray = user.getRoles().stream().map(ur -> ur.role).toArray(String[]::new);

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(roleArray).build();
    }
}
