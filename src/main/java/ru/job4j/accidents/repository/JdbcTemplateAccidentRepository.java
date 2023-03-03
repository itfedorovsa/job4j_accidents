package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;

import java.util.List;
import java.util.Optional;

/**
 * JdbcTemplate Accident repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.03.23
 */
@Repository
@AllArgsConstructor
public class JdbcTemplateAccidentRepository implements AccidentRepository {

    private final JdbcTemplate jdbc;

    private static final String ADD_ACCIDENT = """
            INSERT INTO accidents (name, description, address, type_id) VALUES (?, ?, ?, ?);
            """;

    private static final String FIND_ALL_ACCIDENTS = """
            SELECT a.id, a.name, a.description, a.address, a.type_id, at.id at_id, at.name at_name
            FROM accidents a LEFT JOIN accident_types at ON a.type_id = at.id;
            """;

    private static final String FIND_ACCIDENT_BY_ID = """
            SELECT (id, name, description, address) FROM accidents WHERE id = ?;
            """;

    private static final String UPDATE_ACCIDENT = """
            UPDATE accidents SET name = ?, description = ?, address = ?, type = ? WHERE id = ?;
            """;

    @Override
    public Accident saveAccident(Accident accident) {
        jdbc.update(ADD_ACCIDENT,
                accident.getName(), accident.getDescription(), accident.getAddress(), accident.getType().getId());
        return accident;
    }

    private final RowMapper<Accident> accidentRowMapper = (resultSet, rowNum) -> {
        AccidentType accidentType = new AccidentType();
        accidentType.setId(resultSet.getInt("at_id"));
        accidentType.setName(resultSet.getString("at_name"));
        Accident accident = new Accident();
        accident.setId(resultSet.getInt("id"));
        accident.setName(resultSet.getString("name"));
        accident.setDescription(resultSet.getString("description"));
        accident.setAddress(resultSet.getString("address"));
        accident.setType(accidentType);
        return accident;
    };

    @Override
    public List<Accident> findAllAccidents() {
        return jdbc.query(FIND_ALL_ACCIDENTS, accidentRowMapper);
    }

    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.ofNullable(jdbc.queryForObject(FIND_ACCIDENT_BY_ID, Accident.class, accidentId));
    }

    @Override
    public void updateAccident(Accident accident) {
        jdbc.update(UPDATE_ACCIDENT, accident.getName(), accident.getDescription(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
    }

}
