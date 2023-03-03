package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Memory Accident repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Repository
public class MemoryAccidentRepository implements AccidentRepository {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final AtomicInteger nextId = new AtomicInteger(0);

    private final Logger logger = LoggerFactory.getLogger(MemoryAccidentRepository.class);

    /**
     * Find all Accident
     *
     * @return List of Accident
     */
    @Override
    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    /**
     * Save Accident
     *
     * @param accident Accident
     */
    @Override
    public void saveAccident(Accident accident) {
        int newId = nextId.incrementAndGet();
        accident.setId(newId);
        accidents.put(newId, accident);
    }

    /**
     * Update Accident
     *
     * @param accident Accident
     */
    @Override
    public void updateAccident(Accident accident) {
        int accidentId = accident.getId();
        if (!accidents.containsKey(accidentId)) {
            String errorMessage = "Accident with id " + accidentId + " is missing.";
            logger.error(errorMessage);
            throw new NoSuchElementException(errorMessage);

        }
        accidents.put(accidentId, accident);
    }

    /**
     * Find accident by id
     *
     * @param accidentId Accident id
     * @return Optional of AccidentType or empty Optional
     */
    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.of(accidents.get(accidentId));
    }

}
