package by.itacademy;

import by.itacademy.config.*;
import by.itacademy.security.config.JwtTokenConfig;
import by.itacademy.security.config.WebSecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Import;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = "by.itacademy")
@EnableSwagger2
@EnableCaching
@Import({PersistenceContextBeanConfig.class,
        JwtTokenConfig.class,
        AWSBeanConfig.class,
        AmazonConfig.class,
        CaffeineBeanConfig.class,
        SecurityBeanConfig.class,
        WebSecurityConfig.class,
        SwaggerBeanConfig.class})
public class CinemaApplicationStarter {

    public static void main(String[] args) {
        SpringApplication.run(CinemaApplicationStarter.class, args);
    }
}
