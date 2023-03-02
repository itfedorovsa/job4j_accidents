package ru.job4j.accidents.service;

import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentRepository;

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
public class SimpleAccidentService implements AccidentService {

    AccidentRepository store;

    @Override
    public List<Accident> findAllAccidents() {
        return store.findAllAccidents();
    }

    @Override
    public void saveAccident(Accident accident) {
        store.saveAccident(accident);
    }

    @Override
    public void updateAccident(Accident accident) {
        store.updateAccident(accident);
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return store.findAccidentById(accidentId);
    }

}
