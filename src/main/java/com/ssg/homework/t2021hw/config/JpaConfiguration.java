package com.ssg.homework.t2021hw.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@Configuration
public class JpaConfiguration {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new BasicAuditor();
    }
}
