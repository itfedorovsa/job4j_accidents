package ru.job4j.accidents.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Authority;

import java.util.Optional;

/**
 * Authority Spring Data repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 09.03.23
 */
public interface AuthorityDataRepository extends CrudRepository<Authority, Integer> {

    @Query(value = "SELECT * FROM authorities WHERE authority = ?1", nativeQuery = true)
    Optional<Authority> findAuthorityByName(String authority);

}