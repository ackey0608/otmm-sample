package com.example.initiator;

import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Getter
public class AppProperties {
    private String participant1Url;
    private String participant2Url;
}
