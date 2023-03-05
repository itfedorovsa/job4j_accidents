package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.AccidentType;
import ru.job4j.accidents.model.Article;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

/**
 * JdbcTemplate Accident repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.03.23
 */
/*@Repository*/
@AllArgsConstructor
public class JdbcTemplateAccidentRepository implements AccidentRepository {

    private final JdbcTemplate jdbc;

    private final Logger logger = LoggerFactory.getLogger(JdbcTemplateAccidentRepository.class);

    private static final String SAVE_ACCIDENT = """
            INSERT INTO accidents (name, description, address, type_id) VALUES (?, ?, ?, ?);
            """;

    /**
     * Update accident (not implemented yet)
     */
    private static final String UPDATE_ACCIDENT = """
            UPDATE accidents SET name = ?, description = ?, address = ?, type = ? WHERE id = ?;
            """;

    private static final String FIND_ALL_ACCIDENTS = """
            SELECT a.id, a.name, a.description, a.address, a.type_id, at.id at_id, at.name at_name, ar.id ar_id, ar.name ar_name
            FROM accidents a
            LEFT JOIN accident_types at ON a.type_id = at.id
            LEFT JOIN accidents_articles aa ON aa.accident_id = a.id
            LEFT JOIN articles ar ON aa.article_id = ar.id
            GROUP BY a.id, at.id, ar.id
            ORDER BY a.id asc;
            """;

    private static final String FIND_ACCIDENT_BY_ID = """
            SELECT a.id, a.name, a.description, a.address, a.type_id, at.id at_id, at.name at_name, ar.id ar_id, ar.name ar_name
            FROM accidents a
            LEFT JOIN accident_types at ON a.type_id = at.id
            LEFT JOIN accidents_articles aa ON aa.accident_id = a.id
            LEFT JOIN articles ar ON aa.article_id = ar.id
            WHERE a.id = ?
            """;

    /**
     * Save Accident to db
     *
     * @param accident Accident
     */
    @Override
    public Accident saveAccident(Accident accident) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbc.update(
                connection -> {
                    PreparedStatement ps = connection.prepareStatement(
                            SAVE_ACCIDENT,
                            new String[]{"id"}
                    );
                    ps.setString(1, accident.getName());
                    ps.setString(2, accident.getDescription());
                    ps.setString(3, accident.getAddress());
                    ps.setInt(4, accident.getType().getId());
                    return ps;
                }, keyHolder);
        Integer key = keyHolder.getKeyAs(Integer.class);
        if (key == null) {
            String errorMessage = "The key wasn't assigned";
            logger.error(errorMessage);
            throw new NoSuchElementException(errorMessage);
        }
        accident.setId(key);
        return accident;
    }

    /**
     * Update Accident in db (not implemented yet)
     *
     * @param accident Accident
     */
    @Override
    public void updateAccident(Accident accident) {
        jdbc.update(UPDATE_ACCIDENT, accident.getName(), accident.getDescription(), accident.getAddress(),
                accident.getType().getId(), accident.getId());
    }

    /**
     * Create RowMapper
     */
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
        Set<Article> articles = new HashSet<>();
        int row;
        do {
            articles.add(new Article(resultSet.getInt("ar_id"), resultSet.getString("ar_name")));
            row = resultSet.getRow();
        } while (resultSet.next() && resultSet.getInt("id") == accident.getId());
        resultSet.absolute(row);
        accident.setArticles(articles);
        return accident;
    };

    /**
     * Create PreparedStatementCreator
     *
     * @param query SQL query
     * @return PreparedStatementCreator
     */
    private PreparedStatementCreator getPreparedStatementCreator(String query) {
        return connection -> connection.prepareStatement(query, ResultSet.TYPE_SCROLL_INSENSITIVE,
                ResultSet.CONCUR_UPDATABLE);
    }

    /**
     * Find all Accident from db
     *
     * @return List of Accident
     */
    @Override
    public List<Accident> findAllAccidents() {
        return jdbc.query(getPreparedStatementCreator(FIND_ALL_ACCIDENTS), accidentRowMapper);
    }

    /**
     * Find accident by id from db (not implemented yet)
     *
     * @param accidentId Accident id
     * @return Optional of AccidentType or empty Optional
     */
    @Override
    public Optional<Accident> findAccidentById(int accidentId) {
        return Optional.ofNullable(jdbc.queryForObject(FIND_ACCIDENT_BY_ID, Accident.class, accidentId));
    }

}
