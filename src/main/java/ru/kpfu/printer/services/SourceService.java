package ru.kpfu.printer.services;

import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.printer.security.details.CurrentUser;

/**
 * 12.06.2018
 *
 * @author Robert Bagramov.
 */
public interface SourceService {
    void addTeamDetailsToSourceAndWriteToFile(MultipartFile multipartFile, CurrentUser currentUser);
}
