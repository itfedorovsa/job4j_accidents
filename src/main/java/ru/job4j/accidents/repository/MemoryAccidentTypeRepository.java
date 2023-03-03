package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.AccidentType;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Memory AccidentType repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Repository
public class MemoryAccidentTypeRepository implements AccidentTypeRepository {

    private final List<AccidentType> types = new ArrayList<>();

    public MemoryAccidentTypeRepository() {
        types.add(new AccidentType(1, "Abandoned car"));
        types.add(new AccidentType(2, "One car and environment"));
        types.add(new AccidentType(3, "Two cars"));
        types.add(new AccidentType(4, "Three or more cars"));
        types.add(new AccidentType(5, "Car and person"));
        types.add(new AccidentType(6, "Car and bicycle"));
    }

    /**
     * Find all AccidentType
     *
     * @return List of AccidentType
     */
    @Override
    public List<AccidentType> findAllAccidentTypes() {
        return new ArrayList<>(types);
    }

    /**
     * Find AccidentType by id
     *
     * @param accidentTypeId AccidentType id
     * @return Optional of AccidentType or empty Optional
     */
    @Override
    public Optional<AccidentType> findAccidentTypeById(int accidentTypeId) {
        return Optional.of(types.get(accidentTypeId));
    }

}
