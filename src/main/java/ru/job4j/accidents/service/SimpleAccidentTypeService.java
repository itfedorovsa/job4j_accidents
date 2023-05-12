package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeDataRepository;

import java.util.List;
import java.util.Optional;

/**
 * AccidentType service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Service
@AllArgsConstructor
public class SimpleAccidentTypeService implements AccidentTypeService {

    private final AccidentTypeDataRepository store;

    /**
     * Find all AccidentType from memory
     *
     * @return List of AccidentType
     */
    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return store.findAll();
    }

    /**
     * Find AccidentType by id from memory
     *
     * @param accidentTypeId AccidentType id
     * @return Optional of AccidentType or empty Optional
     */
    @Override
    public Optional<AccidentType> findAccidentTypeById(int accidentTypeId) {
        return store.findById(accidentTypeId);
    }

}
