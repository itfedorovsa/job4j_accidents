package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Hibernate Accident repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 06.03.23
 */
@Repository
@AllArgsConstructor
public class HibernateAccidentRepository implements AccidentRepository {

    private final CrudRepository crudRepository;

    private static final String FIND_ALL_ACCIDENTS = """
            SELECT DISTINCT a
            FROM Accident a
            JOIN FETCH a.type t
            JOIN FETCH a.articles ars
            """;

    private static final String FIND_ACCIDENT_BY_ID = """
            SELECT DISTINCT a
            FROM Accident a
            JOIN FETCH a.type t
            JOIN FETCH a.articles ars
            WHERE a.id = :aId
            """;

    /**
     * Save Accident to db
     *
     * @param accident Accident
     */
    @Override
    public Accident saveAccident(Accident accident) {
        crudRepository.run(session -> session.save(accident));
        return accident;
    }

    /**
     * Update Accident
     *
     * @param accident Accident
     */
    @Override
    public void updateAccident(Accident accident) {
        crudRepository.run(session -> session.update(accident));
    }

    /**
     * Find all Accident
     *
     * @return List of Accident
     */
    @Override
    public List<Accident> findAllAccidents() {
        return crudRepository.query(FIND_ALL_ACCIDENTS,
                Accident.class);
    }

    /**
     * Find Accident by id
     *
     * @param accidentId Accident id
     * @return Optional of AccidentType or empty Optional
     */
    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return crudRepository.optional(FIND_ACCIDENT_BY_ID, Accident.class, Map.of("aId", accidentId));
    }

    /**
     * Delete Accident
     *
     * @param accident Accident
     */
    @Override
    public void deleteAccident(Accident accident) {
        crudRepository.run(session -> session.delete(accident));
    }

}
