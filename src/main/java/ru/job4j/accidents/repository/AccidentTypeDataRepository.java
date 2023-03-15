package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;

/**
 * AccidentType Spring Data repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 15.03.23
 */
public interface AccidentTypeDataRepository extends CrudRepository<AccidentType, Integer> {

    List<AccidentType> findAll();

}
