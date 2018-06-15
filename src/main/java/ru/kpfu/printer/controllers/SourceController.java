package ru.kpfu.printer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.kpfu.printer.security.details.CurrentUser;
import ru.kpfu.printer.services.SourceService;
import ru.kpfu.printer.settings.SourceSettings;

import java.io.File;
import java.io.IOException;

/**
 * 02.06.2018
 *
 * @author Robert Bagramov.
 */
@Controller
public class SourceController {
    private final SourceSettings sourceSettings;
    private final SourceService sourceService;

    @Autowired
    public SourceController(SourceSettings sourceSettings, SourceService sourceService) {
        this.sourceSettings = sourceSettings;
        this.sourceService = sourceService;
    }

    @RequestMapping("/upload")
    public String uploading(Model model) {
        File file = new File(sourceSettings.getUploadFilePath());
        model.addAttribute("files", file.listFiles());
        return "uploading";
    }

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public String uploadingPost(
            @RequestParam("multipartFile") MultipartFile multipartFile,
            @AuthenticationPrincipal CurrentUser currentUser) throws IOException {

        sourceService.addTeamDetailsToSourceAndWriteToFile(multipartFile, currentUser);

        return "redirect:/";
    }
}
