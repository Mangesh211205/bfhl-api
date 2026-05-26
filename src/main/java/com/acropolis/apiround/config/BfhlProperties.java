package com.acropolis.apiround.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@ConfigurationProperties(prefix = "bfhl")
public record BfhlProperties(
        String fullName,
        String dob,
        String email,
        String rollNumber
) {
    public String userId() {
        return fullName.trim().toLowerCase(Locale.ROOT).replaceAll("\\s+", "_") + "_" + dob;
    }
}
