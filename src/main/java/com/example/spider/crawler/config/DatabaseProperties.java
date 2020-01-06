package com.example.spider.crawler.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "db")
@Data
public class DatabaseProperties {

  private String username;
  private String password;
  private String url;

}
