package ru.job4j.accidents.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AccidentService;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.NoSuchElementException;

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
     * Add User in Model by "user" key in all Model in this controller
     *
     * @param httpSession HTTPSession
     * @return User
     */
    @ModelAttribute("user")
    public User addUserToModel(HttpSession httpSession) {
        return getUser(httpSession);
    }

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
        return "accident/allAccidents";
    }

    /**
     * Accident adding form
     *
     * @return formAddAccident.html - accident adding page
     */
    @GetMapping("/formAddAccident")
    public String formAddAccident() {
        return "formAddAccident";
    }

    /**
     * Adding an accident
     *
     * @param accident Accident
     * @return allAccidents.html - all accidents page
     */
    @PostMapping("/addAccident")
    public String addAccident(@ModelAttribute Accident accident) {
        accidentService.saveAccident(accident);
        return "redirect:/allAccidents";
    }

    /**
     * Accident updating method
     *
     * @param model      Model
     * @param accidentId Current Accident id
     * @return updateAccident.html - Accident updating page
     */
    @GetMapping("/formUpdateAccident/{accidentId}")
    public String formUpdateTask(Model model, @PathVariable("accidentId") int accidentId) {
        Accident accidentById = accidentService.findAccidentById(accidentId)
                .orElseThrow(() -> new NoSuchElementException("Accident with id " + accidentId + " is missing."));
        model.addAttribute("accident", accidentById);
        return "accident/updateAccident";
    }

    /**
     * Adding an accident
     *
     * @param accident Accident
     * @return allAccidents.html - all accidents page
     */
    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident) {
        accidentService.updateAccident(accident);
        return "redirect:/allAccidents";
    }

}
