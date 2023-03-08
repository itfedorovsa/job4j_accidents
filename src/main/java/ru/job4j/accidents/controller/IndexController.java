package ru.job4j.accidents.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Index controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 02.03.23
 */
@Controller
public class IndexController implements UserSessionController {

    /**
     * Index page
     *
     * @param model Model
     * @return index.html - start page
     */
    @GetMapping("/index")
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return "index";
    }

}
