package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Authority;
import ru.job4j.accidents.repository.AuthorityDataRepository;

import java.util.Optional;

/**
 * Authority service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 15.03.23
 */
@Service
@AllArgsConstructor
public class SimpleAuthorityService implements AuthorityService {

    private final AuthorityDataRepository store;

    /**
     * Find authority by name
     *
     * @param authority Authority
     * @return Optional of Authority
     */
    @Override
    public Optional<Authority> findAuthorityByName(String authority) {
        return store.findAuthorityByName(authority);
    }

}
