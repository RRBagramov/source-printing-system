package ru.kpfu.printer.settings;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 02.06.2018
 *
 * @author Robert Bagramov.
 */
@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "account")
public class AccountsGenerationSettings {
    private String loginBasePart;
    private int passwordLength;
}
