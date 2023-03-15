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

    @Override
    public Accident saveAccident(Accident accident) {
        store.save(accident);
        return accident;
    }

    @Override
    public void updateAccident(Accident accident) {
        store.save(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return store.findAll();
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return store.findById(accidentId);
    }

    @Override
    public void deleteAccident(Accident accident) {
        store.delete(accident);
    }

}
