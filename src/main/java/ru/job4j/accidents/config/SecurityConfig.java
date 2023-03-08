package ru.job4j.accidents.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * Security config class
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 08.03.23
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    DataSource ds;

    /**
     * Defines how to seek users. All users have roles. Roles defines available user actions.
     *
     * @param auth AuthenticationManagerBuilder
     * @throws Exception Any possible exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(ds)
                .withUser(User.withUsername("user" + System.nanoTime())
                        .password(passwordEncoder().encode("1234"))
                        .roles("USER"));
    }

    /**
     * Password encoder
     *
     * @return BCryptPasswordEncoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Defines description of accesses and login page configuration
     *
     * @param http HttpSecurity
     * @throws Exception Any possible exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }

}