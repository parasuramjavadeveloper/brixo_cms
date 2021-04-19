package cms.brixo.se.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@ConfigurationProperties("spring.datasource")
public class DatabaseConfiguration {

    @Profile("dev")
    @Bean
    public String devDatabaseConnection() {
        return "DB connection for DEV - H2";
    }

    @Profile("test")
    @Bean
    public String testDatabaseConnection() {
        return "DB Connection for TEST - H2";
    }

    @Profile("prod")
    @Bean
    public String prodDatabaseConnection() {
        return "DB Connection for PROD - H2";
    }
}
