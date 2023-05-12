package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.AccidentArticle;
import ru.job4j.accidents.repository.AccidentArticleDataRepository;

/**
 * AccidentArticle service layer
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 05.03.23
 */
@Service
@AllArgsConstructor
public class SimpleAccidentArticleService implements AccidentArticleService {

    private final AccidentArticleDataRepository store;

    /**
     * Save AccidentArticle
     *
     * @param accidentArticle AccidentArticle
     */
    @Override
    public void saveAccidentArticle(AccidentArticle accidentArticle) {
        store.save(accidentArticle);
    }

}
