package by.itacademy.security.configuration;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@EqualsAndHashCode
@Configuration
@ConfigurationProperties("jwtconfig")
public class JwtTokenConfig {

    private String secret;

    private Long expiration;
}
