package ch.elbernito.cmis.adapter;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the CMIS Adapter Spring Boot application.
 * <p>
 * Starts the embedded web server and initializes the Spring context.
 */
@SpringBootApplication
public class CmisAdapterApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args application startup arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(CmisAdapterApplication.class, args);
    }
}
