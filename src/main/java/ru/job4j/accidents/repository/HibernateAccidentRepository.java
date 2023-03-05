package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.List;
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

    private final SessionFactory sf;

    private static final String FIND_ALL_ACCIDENTS = """
    SELECT DISTINCT a
    FROM Accident a
    JOIN FETCH a.type t
    JOIN FETCH a.articles ars
    """;

    /**
     * Save Accident to db
     *
     * @param accident Accident
     */
    @Override
    public Accident saveAccident(Accident accident) {
        try (Session session = sf.openSession()) {
            session.save(accident);
            return accident;
        }
    }

    /**
     * Update Accident (not implemented yet)
     *
     * @param accident Accident
     */
    @Override
    public void updateAccident(Accident accident) {
    }

    /**
     * Find all Accident
     *
     * @return List of Accident
     */
    @Override
    public List<Accident> findAllAccidents() {
        try (Session session = sf.openSession()) {
            return session
                    .createQuery(FIND_ALL_ACCIDENTS, Accident.class)
                    .list();
        }
    }

    /**
     * Find accident by id (not implemented yet)
     *
     * @param accidentId Accident id
     * @return Optional of AccidentType or empty Optional
     */
    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.empty();
    }

}
