package com.example.spider.crawler.controller;

import com.example.spider.crawler.dao.CrawlerDao;
import com.example.spider.crawler.domain.Webpage;
import com.example.spider.crawler.service.Extractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

import static java.util.Collections.*;

@RequiredArgsConstructor
@RestController
public class Controller {

  private final Extractor extractor;

  private final CrawlerDao crawlerDao;

  @RequestMapping("/")
  public String hello(){
    return "hello, go to /swagger-ui.html for a nice ui to use /crawler endpoint.";
  }

  @GetMapping("/crawler")
  public ResponseEntity<String> startWebCrawler(@RequestParam String url) {

    Webpage webpageResponse = extractor.crawl(url); //take response and add to database
    int webPageInserted = crawlerDao.addDataToWebpageTable(webpageResponse.getSearchedUrl(), webpageResponse.getData());//use to get response for swagger
    int adInserted = crawlerDao.addDataToAdTable(webpageResponse.getAdUrl(), webpageResponse.getKeyword()); //use to get response for swagger

    HttpHeaders headers = new HttpHeaders();
    headers.put(HttpHeaders.CONTENT_TYPE, singletonList("application/json"));
    headers.put(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, singletonList("*"));

    if(webPageInserted == 0 && adInserted == 0) {
      return ResponseEntity.ok("Could not crawl webpage or retrieve image for ad.");
    } else if(webPageInserted == 0) {
      return ResponseEntity.ok("Could not crawl webpage");
    } else if(adInserted == 0) {
      return ResponseEntity.ok("Could not retrieve image for ad.");
    }

    return ResponseEntity.ok()
      .headers(headers)
      .build();
  }

}
