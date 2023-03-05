package ru.job4j.accidents.repository;

import lombok.AllArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Article;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * JdbcTemplate Article repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 05.03.23
 */
@Repository
@AllArgsConstructor
public class JdbcTemplateArticleRepository implements ArticleRepository {

    private final JdbcTemplate jdbc;

    private static final String FIND_ALL_ARTICLES = "SELECT * FROM articles";

    private static final String FIND_ARTICLE_BY_ID = """
            SELECT * FROM articles WHERE id = ?;
            """;

    /**
     * Create RowMapper
     */
    private final RowMapper<Article> articleRowMapper = (resultSet, rowNum) -> {
        Article article = new Article();
        article.setId(resultSet.getInt("id"));
        article.setName(resultSet.getString("name"));
        return article;
    };

    /**
     * Find all Article from db
     *
     * @return List of Article
     */
    @Override
    public List<Article> findAllArticles() {
        return jdbc.query(FIND_ALL_ARTICLES, articleRowMapper);
    }

    /**
     * Find Article by id from db
     *
     * @param articleIds Article ids
     * @return Set of Article
     */
    @Override
    public Set<Article> findArticlesById(String[] articleIds) {
        return Arrays.stream(articleIds)
                .map(e -> (jdbc.queryForObject(
                        FIND_ARTICLE_BY_ID, articleRowMapper, Integer.parseInt(e)))).collect(Collectors.toSet());
    }

}
