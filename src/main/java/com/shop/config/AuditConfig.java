package com.shop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing  //①
public class AuditConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {  //②
        return new AuditorAwareImpl();
    }
}

