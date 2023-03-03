package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Article;
import ru.job4j.accidents.repository.ArticleRepository;

import java.util.List;
import java.util.Set;

/**
 * Article service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.03.23
 */
@Service
@AllArgsConstructor
public class SimpleArticleService implements ArticleService {

    ArticleRepository store;

    @Override
    public List<Article> findAllArticles() {
        return store.findAllArticles();
    }

    @Override
    public Set<Article> findArticlesById(String[] articleIds) {
        return store.findArticlesById(articleIds);
    }

}
