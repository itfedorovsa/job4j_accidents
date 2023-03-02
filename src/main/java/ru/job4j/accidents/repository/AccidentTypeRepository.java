package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

/**
 * AccidentType repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
public interface AccidentTypeRepository {

    List<AccidentType> findAllAccidentTypes();

    Optional<AccidentType> findAccidentTypeById(int accidentTypeId);

}
