package ru.job4j.accidents.controller;

import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.accidents.model.Accident;
import ru.job4j.accidents.model.User;
import ru.job4j.accidents.service.AccidentService;
import ru.job4j.accidents.service.AccidentTypeService;
import ru.job4j.accidents.service.ArticleService;

import javax.servlet.http.HttpServletRequest;
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
@AllArgsConstructor
public class AccidentController implements UserSessionController {

    private final AccidentService accidentService;

    private final AccidentTypeService accidentTypeService;

    private final ArticleService articleService;

    private final Logger logger = LoggerFactory.getLogger(AccidentController.class);

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
        System.out.println(allAccidents.size() + "sizeee");
        model.addAttribute("allAccidents", allAccidents);
        return "accident/allAccidents";
    }

    /**
     * Accident adding form
     *
     * @param model Model
     * @return formAddAccident.html - accident adding page
     */
    @GetMapping("/formAddAccident")
    public String formAddAccident(Model model) {
        model.addAttribute("types", accidentTypeService.findAllAccidentTypes());
        model.addAttribute("articles", articleService.findAllArticles());
        return "accident/addAccident";
    }

    /**
     * Adding an accident
     *
     * @param accident Accident
     * @param req      HttpServletRequest
     * @return allAccidents.html - all accidents page
     */
    @PostMapping("/addAccident")
    public String addAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("aIds");
        int accidentTypeId = accident.getType().getId();
        accident.setType(accidentTypeService.findAccidentTypeById(accidentTypeId)
                .orElseThrow(() -> {
                    String errorMessage = "AccidentType with id " + accidentTypeId + " is missing.";
                    logger.error(errorMessage);
                    return new NoSuchElementException(errorMessage);
                }));
        accident.setArticles(articleService.findArticlesById(ids));
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
    @GetMapping("/formUpdateAccident")
    public String formUpdateAccident(Model model, @RequestParam("accidentId") int accidentId) {
        Accident accidentById = accidentService.findAccidentById(accidentId)
                .orElseThrow(() -> {
                    String errorMessage = "Accident with id " + accidentId + " is missing.";
                    logger.error(errorMessage);
                    return new NoSuchElementException(errorMessage);
                });
        model.addAttribute("accident", accidentById);
        model.addAttribute("types", accidentTypeService.findAllAccidentTypes());
        model.addAttribute("articles", articleService.findAllArticles());
        return "accident/updateAccident";
    }

    /**
     * Adding an accident
     *
     * @param accident Accident
     * @param req      HttpServletRequest
     * @return allAccidents.html - all accidents page
     */
    @PostMapping("/updateAccident")
    public String updateAccident(@ModelAttribute Accident accident, HttpServletRequest req) {
        String[] ids = req.getParameterValues("aIds");
        int accidentTypeId = accident.getType().getId();
        accident.setType(accidentTypeService.findAccidentTypeById(accidentTypeId)
                .orElseThrow(() -> {
                    String errorMessage = "AccidentType with id " + accidentTypeId + " is missing.";
                    logger.error(errorMessage);
                    return new NoSuchElementException(errorMessage);
                }));
        accident.setArticles(articleService.findArticlesById(ids));
        accidentService.updateAccident(accident);
        return "redirect:/allAccidents";
    }

}
