package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Memory accident repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Repository
public class MemoryAccidentRepository {

    Map<Integer, Accident> accidents = new ConcurrentHashMap<>();

    public List<Accident> findAllAccidents() {
        return new ArrayList<>(accidents.values());
    }

}
