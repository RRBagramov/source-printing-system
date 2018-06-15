package ru.kpfu.printer.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 12.06.2018
 *
 * @author Robert Bagramov.
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "source")
public class SourceSettings {
    public static final String FILE_EXTENSION = ".txt";
    private String uploadFilePath;
}
