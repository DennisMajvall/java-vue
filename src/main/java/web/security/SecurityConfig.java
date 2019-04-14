package web.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .anyRequest().authenticated()
                .and().formLogin().permitAll()
                .defaultSuccessUrl("/", true)
//                .and().logout().permitAll().logoutSuccessUrl("/login")
                .and().csrf().disable()
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myUserDetailsService())
                .passwordEncoder(MyUserDetailsService.encoder);
    }

    @Bean
    public UserDetailsService myUserDetailsService() {
        return new MyUserDetailsService();
    }
};