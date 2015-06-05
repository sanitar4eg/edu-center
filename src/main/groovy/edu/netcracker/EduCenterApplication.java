package edu.netcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "edu.netcracker")
@EnableJpaRepositories(basePackages = "edu.netcracker.repository")
public class EduCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduCenterApplication.class, args);
    }
}
