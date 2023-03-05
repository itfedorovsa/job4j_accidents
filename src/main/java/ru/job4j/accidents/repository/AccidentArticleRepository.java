package ru.job4j.accidents.repository;

import ru.job4j.accidents.model.Accident;

/**
 * AccidentArticle repository interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 05.03.23
 */
public interface AccidentArticleRepository {

    void saveAccidentArticle(Accident accident);

}
