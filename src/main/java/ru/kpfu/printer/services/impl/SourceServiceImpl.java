package ru.kpfu.printer.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.printer.models.TeamDetails;
import ru.kpfu.printer.models.User;
import ru.kpfu.printer.security.details.CurrentUser;
import ru.kpfu.printer.services.SourceService;
import ru.kpfu.printer.services.UserService;
import ru.kpfu.printer.settings.SourceSettings;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static ru.kpfu.printer.settings.SourceSettings.FILE_EXTENSION;

/**
 * 12.06.2018
 *
 * @author Robert Bagramov.
 */
@Service
public class SourceServiceImpl implements SourceService {
    @Autowired
    private UserService userService;
    @Autowired
    private SourceSettings sourceSettings;

    @Override
    public void addTeamDetailsToSourceAndWriteToFile(MultipartFile multipartFile, CurrentUser currentUser) {
        User user = userService.findUserByUsername(currentUser.getUsername());
        TeamDetails teamDetails = user.getTeamDetails();
        StringBuilder header = new StringBuilder();
        header
                .append(teamDetails.getTeamName() + "\n")
                .append(teamDetails.getAuditory() + "\n")
                .append(teamDetails.getMoreInfo() + "\n");

        BufferedOutputStream bos = null;
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(
                    sourceSettings.getUploadFilePath() +
                            currentUser.getUsername() +
                            FILE_EXTENSION);
            bos = new BufferedOutputStream(fos);
            bos.write(header.toString().getBytes());
            bos.write(multipartFile.getBytes());
        } catch (FileNotFoundException fnfe) {
            System.out.println("File not found" + fnfe);
        } catch (IOException ioe) {
            System.out.println("Error while writing to file" + ioe);
        } finally {
            try {
                if (bos != null) {
                    bos.flush();
                    bos.close();
                }
            } catch (Exception e) {
                System.out.println("Error while closing streams" + e);
            }
        }
    }
}
