package com.example.initiator;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("app")
@Data
public class AppProperties {
    private String participant1Url;
    private String participant2Url;
}
