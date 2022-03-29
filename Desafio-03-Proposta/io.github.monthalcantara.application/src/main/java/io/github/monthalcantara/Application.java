package io.github.monthalcantara;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = {"io.github.monthalcantara.entities"})
@EnableJpaRepositories(basePackages =  {"io.github.monthalcantara.repository"})
@SpringBootApplication(scanBasePackages = "io.github.monthalcantara")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
