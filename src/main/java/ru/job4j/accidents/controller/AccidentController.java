package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.service.AccidentService;

import java.util.List;

/**
 * Accident controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Controller
public class AccidentController implements UserSessionController {

    AccidentService accidentService;

    /**
     * All accidents page
     *
     * @param model Model
     * @return allAccidents.html - all accidents from memory
     */
    @GetMapping("/allAccidents")
    public String allAccidents(Model model) {
        List<Accident> allAccidents = accidentService.findAllAccidents();
        model.addAttribute("allAccidents", allAccidents);
        return "accidents/allAccidents";
    }

}
