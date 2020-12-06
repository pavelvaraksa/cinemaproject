package by.itacademy;

import by.itacademy.config.CinemaApplicationConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "by.itacademy")
@EnableSwagger2
@Import({CinemaApplicationConfig.class})
public class CinemaApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplicationStarter.class, args);
    }
}
