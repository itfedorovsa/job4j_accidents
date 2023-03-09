package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.AuthorityRepository;
import ru.job4j.accidents.repository.UserDataRepository;

/**
 * Registration controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Controller
@AllArgsConstructor
public class RegistrationController {

    private final PasswordEncoder encoder;

    private final UserDataRepository users;

    private final AuthorityRepository authorities;

    /**
     * Register a User
     *
     * @param user User
     * @return login.html - login page
     */
    @PostMapping("/registerUser")
    public String registerUser(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("ROLE_USER"));
        users.save(user);
        return "redirect:/login";
    }

    /**
     * Registration method
     *
     * @return registration.html - Registration page
     */
    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

}
