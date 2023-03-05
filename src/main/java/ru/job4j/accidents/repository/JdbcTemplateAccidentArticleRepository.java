package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Accident;

/**
 * JdbcTemplate AccidentArticle repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 05.03.23
 */
@Repository
@AllArgsConstructor
public class JdbcTemplateAccidentArticleRepository implements AccidentArticleRepository {

    private final JdbcTemplate jdbc;

    private static final String ADD_ACCIDENT_ARTICLE = """
            INSERT INTO accidents_articles (accident_id, article_id) VALUES (?, ?);
            """;

    /**
     * Save AccidentArticle to db
     *
     * @param accident Accident
     */
    @Override
    public void saveAccidentArticle(Accident accident) {
        accident.getArticles()
                .forEach(
                        article -> jdbc.update(
                                ADD_ACCIDENT_ARTICLE,
                                accident.getId(),
                                article.getId())
                );
    }

}
