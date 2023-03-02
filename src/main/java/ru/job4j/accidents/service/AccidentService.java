package ru.job4j.accidents.service;

import ru.job4j.accidents.model.Accident;

import java.util.List;

/**
 * Accident service interface
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
public interface AccidentService {

    List<Accident> findAllAccidents();

}
