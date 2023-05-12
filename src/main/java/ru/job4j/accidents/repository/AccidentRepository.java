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

    /**
     * Save accident to db
     *
     * @param accident Accident
     * @return Accident
     */
    Accident saveAccident(Accident accident);

    /**
     * Update accident
     *
     * @param accident Accident
     */
    void updateAccident(Accident accident);

    /**
     * Find all accidents
     *
     * @return List of Accident
     */
    List<Accident> findAllAccidents();

    /**
     * Find accident by id
     *
     * @param accidentId Accident id
     * @return Optional of Accident
     */
    Optional<Accident> findAccidentById(int accidentId);

    /**
     * Delete accident
     *
     * @param accident Accident
     */
    void deleteAccident(Accident accident);

}
