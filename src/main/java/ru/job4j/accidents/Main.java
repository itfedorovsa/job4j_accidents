package ru.job4j.accidents;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Application launch class
 *
 * @author itfedorovsa (itfedorovsa@gmail.com)
 * @version 1.0
 * @since 03.03.23
 */
@SpringBootApplication
public class Main {

    /**
     * Starts the application
     *
     * @param args app arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
        System.out.println("Go to http://localhost:8080/index");
    }

}


