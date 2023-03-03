package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Article;

import java.util.List;
import java.util.Set;

/**
 * Article repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.03.23
 */
public interface ArticleRepository {

    List<Article> findAllArticles();

    Set<Article> findArticlesById(String[] articleIds);

}
