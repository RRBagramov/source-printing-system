package ru.kpfu.printer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.printer.models.enums.UserRole;
import ru.kpfu.printer.services.AuthService;

import java.util.Optional;

/**
 * 01.05.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping({"/", "/login"})
    public String login(@ModelAttribute("model") ModelMap model,
                        Authentication authentication,
                        @RequestParam Optional<String> error) {
        //TODO: admin page, team page
        if (authentication != null) {
            String currentUserRole = authService.getCurrentUserRole(authentication);
            if (currentUserRole.equals(UserRole.ADMIN.name())) {
                return "success_page";//return "adminPage";
            } else {
                return "uploading";
            }
        }
        model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }
}
