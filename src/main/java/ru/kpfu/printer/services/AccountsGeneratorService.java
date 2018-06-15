package ru.kpfu.printer.services;

import ru.kpfu.printer.dto.TeamDto;

import java.util.List;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
public interface AccountsGeneratorService {
    public List<TeamDto> generateTeamsAccounts(int count);

    List<TeamDto> findAllAccounts();
}
