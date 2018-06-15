package ru.kpfu.printer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.kpfu.printer.dto.TeamDto;
import ru.kpfu.printer.models.TeamDetails;
import ru.kpfu.printer.models.User;
import ru.kpfu.printer.repositories.TeamDetailsRepository;
import ru.kpfu.printer.repositories.UserRepository;
import ru.kpfu.printer.services.UserService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Autowired
    private TeamDetailsRepository teamDetailsRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void submitAllTeamsDetails(List<TeamDto> users) {
        List<User> updateUsersList = new ArrayList<>();

        for (TeamDto teamDto : users) {
            Optional<User> optionalUser = userRepository.findById(teamDto.getUserId());
            optionalUser.ifPresent(user -> {
                TeamDetails teamDetails = new TeamDetails();

                teamDetails.setTeamName(teamDto.getTeamName());
                teamDetails.setAuditory(teamDto.getAuditory());
                teamDetails.setMoreInfo(teamDto.getMoreInfo());
                user.setTeamDetails(teamDetails);
                updateUsersList.add(user);
            });
        }

        userRepository.saveAll(updateUsersList);
    }

    @Override
    public void deleteAllTeams() {
        teamDetailsRepository.deleteAll();
    }

    @Override
    public void deleteOneTeam(int id) {
        teamDetailsRepository.deleteById(id);
    }

    public User findUserByUsername(String username) {
        return userRepository.findOneByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not-found"));
    }
}
