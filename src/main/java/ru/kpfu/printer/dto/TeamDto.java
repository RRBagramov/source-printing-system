package ru.kpfu.printer.dto;

import lombok.*;
import ru.kpfu.printer.models.TeamDetails;
import ru.kpfu.printer.models.User;

import java.util.List;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isBlank;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TeamDto {
    private String login;
    private String password;
    private int userId;
    private int teamDetailsId;
    private String teamName = "";
    private String auditory = "";
    private String moreInfo = "";

    public static TeamDto buildFromModel(User user) {
        TeamDetails teamDetails = user.getTeamDetails();
        TeamDto teamDto = new TeamDto();
        teamDto.userId = user.getId();
        teamDto.teamDetailsId = teamDetails.getId();
        teamDto.login = user.getUsername();
        teamDto.password = user.getPassword();

        if (!isBlank(teamDetails.getAuditory())) {
            teamDto.auditory = teamDetails.getAuditory();
        }
        if (!isBlank(teamDetails.getTeamName())) {
            teamDto.auditory = teamDetails.getTeamName();
        }
        if (!isBlank(teamDetails.getMoreInfo())) {
            teamDto.auditory = teamDetails.getMoreInfo();
        }

        return teamDto;
    }

    public static List<TeamDto> buildFromModels(List<User> users) {
        return users
                .stream()
                .map(TeamDto::buildFromModel)
                .collect(Collectors.toList());
    }

}