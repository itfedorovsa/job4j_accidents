package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Accident;

import java.util.List;

/**
 * Accident Spring Data repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.03.23
 */
public interface AccidentDataRepository extends CrudRepository<Accident, Integer> {

    /**
     * Find all accidents
     *
     * @return List of Accident
     */
    List<Accident> findAll();

}
