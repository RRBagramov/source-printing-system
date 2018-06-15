package ru.kpfu.printer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.printer.dto.TeamDto;
import ru.kpfu.printer.models.TeamDetails;
import ru.kpfu.printer.models.User;
import ru.kpfu.printer.models.enums.UserRole;
import ru.kpfu.printer.repositories.UserRepository;
import ru.kpfu.printer.services.AccountsGeneratorService;
import ru.kpfu.printer.settings.AccountsGenerationSettings;
import ru.kpfu.printer.utils.AccountGenerator;

import java.util.*;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class AccountsGeneratorServiceImpl implements AccountsGeneratorService {
    private final UserRepository userRepository;
    private final AccountsGenerationSettings generationSettings;

    @Autowired
    public AccountsGeneratorServiceImpl(UserRepository userRepository, AccountsGenerationSettings generationSettings) {
        this.userRepository = userRepository;
        this.generationSettings = generationSettings;
    }

    @Override
    public List<TeamDto> generateTeamsAccounts(int count) {
        Map<String, String> accounts = AccountGenerator.generateAccounts(
                count, generationSettings.getPasswordLength(), generationSettings.getLoginBasePart());

        User user;
        List<User> newUsers = new ArrayList<>();
        for (Map.Entry<String, String> entry : accounts.entrySet()) {
            user = new User();
            user.setUsername(entry.getKey());
            user.setPassword(entry.getValue());
            user.setRole(UserRole.TEAM);
            TeamDetails teamDetails = new TeamDetails();
            teamDetails.setUser(user);
            user.setTeamDetails(teamDetails);

            newUsers.add(user);
        }
        userRepository.saveAll(newUsers);

        Optional<List<User>> optionalAllTeams = userRepository.findAllByRole(UserRole.TEAM);
        List<User> users = optionalAllTeams.orElse(Collections.emptyList());

        return TeamDto.buildFromModels(users);
    }

    @Override
    public List<TeamDto> findAllAccounts() {
        Optional<List<User>> users = userRepository.findAllByRole(UserRole.TEAM);
        return TeamDto.buildFromModels(users.orElse(Collections.emptyList()));
    }

}
