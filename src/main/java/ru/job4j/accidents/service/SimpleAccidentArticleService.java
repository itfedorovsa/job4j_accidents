package ru.job4j.accidents.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.repository.AccidentArticleRepository;

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

    private final AccidentArticleRepository store;

    @Override
    public void saveAccidentArticle(Accident accident) {
        store.saveAccidentArticle(accident);
    }

}
