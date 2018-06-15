package ru.kpfu.printer.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * 31.05.2018
 *
 * @author Robert Bagramov.
 */
@SpringBootApplication
@EntityScan("ru.kpfu.printer.models")
@ComponentScan("ru.kpfu")
@EnableJpaRepositories(basePackages = "ru.kpfu.printer.repositories")
public class SourcePrintingApp {
    public static void main(String[] args) {
        SpringApplication.run(SourcePrintingApp.class, args);
    }
}
