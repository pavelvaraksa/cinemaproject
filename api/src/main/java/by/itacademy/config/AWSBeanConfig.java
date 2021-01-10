package by.itacademy.config;

import org.springframework.context.annotation.Bean;
import software.amazon.awssdk.auth.credentials.AwsBasicCredentials;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;

public class AWSBeanConfig {

    @Bean
    public S3Client s3Client(AmazonConfig amazonConfiguration) {
        return S3Client
                .builder()
                .region(Region.of(amazonConfiguration.getRegion()))
                .credentialsProvider(() ->
                        AwsBasicCredentials.create(
                                amazonConfiguration.getAccessKeyId(),
                                amazonConfiguration.getSecretKey()
                        ))
                .build();
    }
}
