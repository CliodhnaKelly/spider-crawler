package com.example.spider.crawler.dao;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.Instant;

@Slf4j
@RequiredArgsConstructor
@Component
public class CrawlerDao {

  @NonNull
  private final DataSource dataSource;

  public int addDataToWebpageTable(String url, String data){
    String insert = "INSERT INTO webpage (url, time_indexed_at, raw_data) VALUES (?,?,?)"; //id will auto-increment
    try(Connection connection = dataSource.getConnection()) {
      try(PreparedStatement ps = connection.prepareStatement(insert)) {
        ps.setString(1, url);
        ps.setTimestamp(2, Timestamp.from(Instant.now()));
        ps.setString(3, data);

        return ps.executeUpdate();
      }
    } catch (SQLException e) {
      log.info("Error:" + e);
    }

    return 0;
  }

  public int addDataToAdTable(String url, String keyword) {
    String insert = "INSERT INTO ads (url, keyword) VALUES (?,?)";
    try(Connection connection = dataSource.getConnection()) {
      try(PreparedStatement ps = connection.prepareStatement(insert)) {
        ps.setString(1, url);
        ps.setString(2, keyword);

        return ps.executeUpdate();
      }
    } catch (SQLException e) {
      log.info("Error: " + e);
    }
    return 0;
  }
}
