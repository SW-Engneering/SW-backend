package com.swengineer.sportsmatch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class JwtConfig {

    @Value("${jwt.secret-key}")
    private String secretKey;

    @Value("${jwt.expiration-time}")
    private long expirationTime;

    @Value("${jwt.token-prefix}")
    private String tokenPrefix;

    @Value("${jwt.header-string}")
    private String headerString;

    // Getter 메서드들
    public String getSecretKey() {
        return secretKey;
    }

    public long getExpirationTime() {
        return expirationTime;
    }

    public String getTokenPrefix() {
        return tokenPrefix;
    }

    public String getHeaderString() {
        return headerString;
    }
}
