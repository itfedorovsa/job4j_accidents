package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.repository.AccidentTypeRepository;

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
public class SimpleAccidentTypeService implements AccidentTypeService {

    AccidentTypeRepository store;

    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return store.findAllAccidentTypes();
    }

    @Override
    public Optional<AccidentType> findAccidentTypeById(int accidentTypeId) {
        return store.findAccidentTypeById(accidentTypeId);
    }

}
