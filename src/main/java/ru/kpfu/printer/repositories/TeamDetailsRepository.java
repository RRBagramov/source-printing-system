package ru.kpfu.printer.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.printer.models.TeamDetails;

/**
 * 02.06.2018
 *
 * @author Robert Bagramov.
 */
public interface TeamDetailsRepository extends CrudRepository<TeamDetails, Integer> {
}
