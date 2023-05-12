package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Article;

import java.util.List;
import java.util.Set;

/**
 * Article service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.03.23
 */
public interface ArticleService {

    /**
     * Find all Article
     *
     * @return List of Article
     */
    List<Article> findAllArticles();

    /**
     * Find Article by id
     *
     * @param articleIds Article ids
     * @return Set of Article
     */
    Set<Article> findArticlesById(String[] articleIds);

}
