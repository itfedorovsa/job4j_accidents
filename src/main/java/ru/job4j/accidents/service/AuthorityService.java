package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Authority;

import java.util.Optional;

/**
 * Authority service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 15.03.23
 */
public interface AuthorityService {

    Optional<Authority> findAuthorityByName(String authority);

}
