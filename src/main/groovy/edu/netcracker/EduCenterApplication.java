package edu.netcracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = "edu.netcracker")
@EnableJpaRepositories(basePackages = "edu.netcracker.repository")
@EnableTransactionManagement
public class EduCenterApplication {

    public static void main(String[] args) {
        SpringApplication.run(EduCenterApplication.class, args);
    }
}
