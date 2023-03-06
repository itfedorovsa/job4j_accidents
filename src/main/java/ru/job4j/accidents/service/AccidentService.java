package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

/**
 * Accident service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
public interface AccidentService {

    Accident saveAccident(Accident accident);

    void updateAccident(Accident accident);

    List<Accident> findAllAccidents();

    Optional<Accident> findAccidentById(int accidentId);

    void deleteAccident(Accident accident);

}
