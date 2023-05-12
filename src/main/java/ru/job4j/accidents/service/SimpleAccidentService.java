package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentDataRepository;

import java.util.List;
import java.util.Optional;

/**
 * Accident service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Service
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private final AccidentDataRepository store;

    /**
     * Save accident
     *
     * @param accident Accident
     * @return Accident
     */
    @Override
    public Accident saveAccident(Accident accident) {
        store.save(accident);
        return accident;
    }

    /**
     * Update accident
     *
     * @param accident Accident
     */
    @Override
    public void updateAccident(Accident accident) {
        store.save(accident);
    }

    /**
     * Find all accidents
     *
     * @return List of Accident
     */
    @Override
    public List<Accident> findAllAccidents() {
        return store.findAll();
    }

    /**
     * Find accident by id
     *
     * @param accidentId Accident id
     * @return Optional of Accident
     */
    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return store.findById(accidentId);
    }

    /**
     * Delete accident
     *
     * @param accident Accident
     */
    @Override
    public void deleteAccident(Accident accident) {
        store.delete(accident);
    }

}
