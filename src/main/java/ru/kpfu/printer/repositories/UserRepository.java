package ru.kpfu.printer.repositories;

import org.springframework.data.repository.CrudRepository;
import ru.kpfu.printer.models.User;
import ru.kpfu.printer.models.enums.UserRole;

import java.util.List;
import java.util.Optional;

/**
 * 01.06.2018
 *
 * @author Robert Bagramov.
 */
public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findOneByUsername(String username);

    Optional<List<User>> findAllByRole(UserRole role);
}
