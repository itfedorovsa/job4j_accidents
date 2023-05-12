package ru.job4j.accidents.repository;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.accidents.model.Article;

import java.util.List;

/**
 * Article Spring Data repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 07.03.23
 */
public interface ArticleDataRepository extends CrudRepository<Article, Integer> {

    /**
     * Find all articles
     *
     * @return List of Article
     */
    List<Article> findAll();

}
