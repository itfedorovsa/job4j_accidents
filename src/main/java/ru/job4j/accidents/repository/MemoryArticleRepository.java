package ru.job4j.accidents.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.accidents.model.Article;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Memory Article repository
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.03.23
 */
@Repository
public class MemoryArticleRepository implements ArticleRepository {

    private final List<Article> articles = new ArrayList<>();

    public MemoryArticleRepository() {
        articles.add(new Article(0, "Article 2"));
        articles.add(new Article(1, "Article 5"));
        articles.add(new Article(2, "Article 6 "));
        articles.add(new Article(3, "Article 7"));
        articles.add(new Article(4, "Article 11"));
        articles.add(new Article(5, "Article 13"));
        articles.add(new Article(6, "Article 16"));
    }

    /**
     * Find all Article
     *
     * @return List of Article
     */
    @Override
    public List<Article> findAllArticles() {
        return new ArrayList<>(articles);
    }

    /**
     * Find Article by id
     *
     * @param articleIds Article ids
     * @return Set of Article
     */
    public Set<Article> findArticlesById(String[] articleIds) {
        Set<Article> arts = new HashSet<>();
        for (String id : articleIds) {
            arts.add(articles.get(Integer.parseInt(id)));
        }
        return arts;
    }

}
