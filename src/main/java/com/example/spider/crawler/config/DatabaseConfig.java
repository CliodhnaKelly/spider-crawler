package com.example.spider.crawler.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.flywaydb.core.Flyway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class DatabaseConfig {

  @NonNull
  private final DatabaseProperties databaseProperties;

  @Bean
  public Flyway migrate(DataSource dataSource) {
    Flyway flyway = Flyway.configure()
      .dataSource(dataSource)
      .schemas("spiderdb")
      .locations("classpath:db/migration")
      .baselineOnMigrate(true)
      .load();

    flyway.migrate();
    return flyway;
  }

  @Bean
  public DataSource dataSource() {
    HikariConfig config = new HikariConfig();
    config.setDriverClassName(org.postgresql.Driver.class.getName());
    config.setJdbcUrl(databaseProperties.getUrl());
    config.setUsername(databaseProperties.getUsername());
    config.setPassword(databaseProperties.getPassword());
    config.setSchema("spiderdb");
    return new HikariDataSource(config);
  }
}
