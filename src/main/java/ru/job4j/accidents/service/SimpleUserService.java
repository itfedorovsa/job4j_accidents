package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.repository.UserDataRepository;

/**
 * User service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 09.03.23
 */
@Service
@AllArgsConstructor
public class SimpleUserService implements UserService {

    private final UserDataRepository store;

    /**
     * Save user
     *
     * @param user User
     * @return User
     */
    @Override
    public User saveUser(User user) {
        store.save(user);
        return user;
    }

}
