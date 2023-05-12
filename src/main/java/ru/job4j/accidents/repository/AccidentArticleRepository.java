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

    /**
     * Save AccidentArticle to db
     *
     * @param accident Accident
     */
    void saveAccidentArticle(Accident accident);

}
