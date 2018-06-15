package ru.kpfu.printer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.kpfu.printer.dto.TeamDto;
import ru.kpfu.printer.services.AccountsGeneratorService;
import ru.kpfu.printer.services.UserService;

import java.util.List;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class AccountsController {
    private final UserService userService;
    private final AccountsGeneratorService generatorService;

    @Autowired
    public AccountsController(UserService userService, AccountsGeneratorService generatorService) {
        this.userService = userService;
        this.generatorService = generatorService;
    }

    @GetMapping("admin/accounts")
    public String getAccounts(Model model) {
        List<TeamDto> accounts = generatorService.findAllAccounts();
        model.addAttribute("accounts", accounts);

        return "accounts_page";
    }

    @GetMapping("admin/accounts/generator")
    public String generateAccounts(Model model, @RequestParam("count") int count) {
        List<TeamDto> accounts = generatorService.generateTeamsAccounts(count);
        model.addAttribute("accounts", accounts);

        return "accounts_page";
    }

    @GetMapping("admin/accounts/delete/all")
    public String deleteAccounts() {
        userService.deleteAllTeams();
        return "generate_page";
    }

    @GetMapping("admin/accounts/delete/{id}")
    public String deleteAccount(@PathVariable("id") int id) {
        userService.deleteOneTeam(id);
        return "accounts_page";
    }

    @PostMapping("admin/accounts/edit")
    public String editAccounts(@RequestParam("TeamDtoList") List<TeamDto> teamDtoList) {
        userService.submitAllTeamsDetails(teamDtoList);

        return "accounts_page";
    }


}
