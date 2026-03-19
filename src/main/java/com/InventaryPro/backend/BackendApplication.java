package com.InventaryPro.backend;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@SpringBootApplication
public class BackendApplication {
 private static void setIfPresent(Dotenv dotenv, String key) {
        String value = dotenv.get(key);
        if (value != null) {
            System.setProperty(key, value);
        }
    }
	public static void main(String[] args) {

        Dotenv dotenv = Dotenv.configure()
                .ignoreIfMissing()
                .load();

        setIfPresent(dotenv, "DB_URL");
        setIfPresent(dotenv, "DB_USERNAME");
        setIfPresent(dotenv, "DB_PASSWORD");


        SpringApplication.run(BackendApplication.class, args);
	}


}
