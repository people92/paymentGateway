package com.ssg.homework.t2021hw.config;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class BasicAuditor implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of("payment-gateway");
    }
}
