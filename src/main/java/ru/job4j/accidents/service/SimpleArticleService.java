package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Article;
import ru.job4j.accidents.repository.ArticleDataRepository;

import java.util.Arrays;
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

    private final ArticleDataRepository store;

    @Override
    public List<Article> findAllArticles() {
        return store.findAll();
    }

    @Override
    public Set<Article> findArticlesById(String[] articleIds) {
        return Streamable.of(
                store.findAllById(Arrays.stream(articleIds).map(Integer::parseInt).toList())
        ).toSet();
    }

}
