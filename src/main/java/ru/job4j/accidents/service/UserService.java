package ru.job4j.accidents.service;

import ru.job4j.accidents.model.User;

/**
 * User service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 09.03.23
 */
public interface UserService {

    /**
     * Save user
     *
     * @param user User
     * @return User
     */
    User saveUser(User user);

}
