package edu.jennifer.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by khalid on 4/17/17.
 */

@SpringBootApplication
@EnableJpaRepositories
public class App {

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);


    }

}
