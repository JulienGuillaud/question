package fr.gamedev.question;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author djer1
 *
 */
@SuppressWarnings({ "checkstyle:HideUtilityClassConstructor" })
@SpringBootApplication
public class Launcher {

    /**
     * @param args command line params
     */
    public static void main(final String[] args) {
        SpringApplication.run(Launcher.class, args);
    }

}
