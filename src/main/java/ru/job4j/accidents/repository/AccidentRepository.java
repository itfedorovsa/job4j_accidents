package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Optional;

/**
 * Accident repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
public interface AccidentRepository {

    List<Accident> findAllAccidents();

    Optional<Accident> findAccidentById(int accidentId);

    Accident saveAccident(Accident accident);

    void updateAccident(Accident accident);

}
