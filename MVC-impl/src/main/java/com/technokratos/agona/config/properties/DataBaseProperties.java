package com.technokratos.agona.config.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class DataBaseProperties {

    private final String url;
    private final String username;
    private final String password;
    private final String driverClassName;
    private final int maxPoolSize;

    public DataBaseProperties(@Value("${db.url}") String url,
                              @Value("${db.username}") String username,
                              @Value("${db.password}") String password,
                              @Value("${db.driver.classname}") String driverClassName,
                              @Value("${db.hikari.max-pool-size}") int maxPoolSize) {
        this.url = url;
        this.username = username;
        this.password = password;
        this.driverClassName = driverClassName;
        this.maxPoolSize = maxPoolSize;
    }

}

