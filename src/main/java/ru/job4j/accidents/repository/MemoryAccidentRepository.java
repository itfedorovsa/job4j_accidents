package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Memory accident repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Repository
public class MemoryAccidentRepository implements AccidentRepository {

    private final Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    private final AtomicInteger nextId = new AtomicInteger(0);

    @Override
    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

    @Override
    public void saveAccident(Accident accident) {
        int newId = nextId.incrementAndGet();
        accident.setId(newId);
        accidents.put(newId, accident);
    }

    @Override
    public void updateAccident(Accident accident) {
        int accidentId = accident.getId();
        if (accidents.containsKey(accidentId)) {
            accidents.put(accidentId, accident);
        } else {
            throw new IllegalArgumentException("An accident with this id is missing");
        }
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.of(accidents.get(accidentId));
    }

}
