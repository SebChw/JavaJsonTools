package pl.put.poznan.jsontools.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Class for running Spring Boot Json transformer.
 */

@SpringBootApplication(scanBasePackages = {"pl.put.poznan.jsontools.rest"})
public class JsonToolsApplication {

    /**
     * Runs Spring Json Application
     * @param args optional
     */
    public static void main(String[] args) {
        SpringApplication.run(JsonToolsApplication.class, args);
    }
}
