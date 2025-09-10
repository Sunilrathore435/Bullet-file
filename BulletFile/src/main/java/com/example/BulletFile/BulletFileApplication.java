package com.example.BulletFile;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class BulletFileApplication {

    public static void main(String[] args) {
        // This code block loads variables from the .env file and sets them as system properties.
        Dotenv dotenv = Dotenv.configure().ignoreIfMalformed().load();
        dotenv.entries().forEach(entry ->
            System.setProperty(entry.getKey(), entry.getValue())
        );

        SpringApplication.run(BulletFileApplication.class, args);
    }

}
