package ru.kpfu.printer.services;

import ru.kpfu.printer.dto.TeamDto;
import ru.kpfu.printer.models.User;

import java.util.List;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */

public interface UserService {
    void submitAllTeamsDetails(List<TeamDto> teams);

    void deleteAllTeams();

    void deleteOneTeam(int id);

    User findUserByUsername(String username);
}
