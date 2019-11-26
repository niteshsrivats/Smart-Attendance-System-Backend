package attendance.system.central;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(
        basePackages = {
                "attendance.system.central.controller",
                "attendance.system.central.config",
                "attendance.system.central.models",
                "attendance.system.central.repositories",
                "attendance.system.central.service"
        }
)
@EnableJpaRepositories(basePackages = "attendance.system.central.repositories.postgres")

public class CentralApplication {

    public static void main(String[] args) {
        SpringApplication.run(CentralApplication.class, args);
    }
}
