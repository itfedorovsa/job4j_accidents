package ru.job4j.accidents.service;

import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

/**
 * AccidentType service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
public interface AccidentTypeService {

    /**
     * Find all AccidentType from memory
     *
     * @return List of AccidentType
     */
    List<AccidentType> findAllAccidentTypes();

    /**
     * Find AccidentType by id from memory
     *
     * @param accidentTypeId AccidentType id
     * @return Optional of AccidentType or empty Optional
     */
    Optional<AccidentType> findAccidentTypeById(int accidentTypeId);

}
