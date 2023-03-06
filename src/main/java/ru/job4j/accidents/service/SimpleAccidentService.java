package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentArticleRepository;
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
@AllArgsConstructor
public class SimpleAccidentService implements AccidentService {

    private final AccidentRepository store;

    private final AccidentArticleRepository accidentArticleRepository;

    @Override
    public Accident saveAccident(Accident accident) {
        store.saveAccident(accident);
        return accident;
    }

    @Override
    public void updateAccident(Accident accident) {
        store.updateAccident(accident);
    }

    @Override
    public List<Accident> findAllAccidents() {
        return store.findAllAccidents();
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return store.findAccidentById(accidentId);
    }

    @Override
    public void deleteAccident(Accident accident) {
        store.deleteAccident(accident);
    }

}
