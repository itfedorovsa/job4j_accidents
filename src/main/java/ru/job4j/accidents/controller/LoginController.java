package ru.job4j.accidents.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Login controller
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 08.03.23
 */
@Controller
public class LoginController {

    /**
     * Login page
     *
     * @param error  Error string
     * @param logout Logout string
     * @param model  Model
     * @return login.html - login page
     */
    @GetMapping("/login")
    public String login(@RequestParam(value = "error", required = false) String error,
                        @RequestParam(value = "logout", required = false) String logout,
                        Model model) {
        String errorMessage = null;
        if (error != null) {
            errorMessage = "Username or Password is incorrect!";
        }
        if (logout != null) {
            errorMessage = "You have been successfully logged out!";
        }
        model.addAttribute("errorMessage", errorMessage);
        return "login";
    }

    /**
     * Log out page
     *
     * @param request  Request
     * @param response Response
     * @return login.html - login page
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout=true";
    }

}
