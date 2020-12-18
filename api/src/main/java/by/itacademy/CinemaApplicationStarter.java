package by.itacademy;

import by.itacademy.config.JdbcBeanConfig;
import by.itacademy.config.PersistenceContextBeansConfig;
import by.itacademy.config.SwaggerBeanConfig;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Log4j
@SpringBootApplication(scanBasePackages = "by.itacademy")
@EnableSwagger2
@Import({JdbcBeanConfig.class,
        PersistenceContextBeansConfig.class,
        SwaggerBeanConfig.class})
public class CinemaApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplicationStarter.class, args);
    }
}
