package edu.havrysh.lab8.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.domain.AuditorAware;
import org.springframework.context.annotation.Bean;

@Configuration
@EnableMongoAuditing
public class AuditionConfiguration {
    @Bean
    public AuditorAware<String> auditorAware() {
        return new AuditorAwareImpl();
    }
}

