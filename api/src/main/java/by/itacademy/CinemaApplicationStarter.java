package by.itacademy;

import by.itacademy.config.CaffeineBeanConfig;
import by.itacademy.config.PersistenceContextBeanConfig;
import by.itacademy.config.SecurityBeanConfig;
import by.itacademy.config.SwaggerBeanConfig;
import by.itacademy.security.config.JwtTokenConfig;
import by.itacademy.security.config.WebSecurityConfig;
import lombok.extern.log4j.Log4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Log4j
@SpringBootApplication(scanBasePackages = "by.itacademy")
@EnableSwagger2
@EnableCaching
@Import({PersistenceContextBeanConfig.class,
        JwtTokenConfig.class,
        CaffeineBeanConfig.class,
        SecurityBeanConfig.class,
        WebSecurityConfig.class,
        SwaggerBeanConfig.class})
public class CinemaApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplicationStarter.class, args);
    }
}
