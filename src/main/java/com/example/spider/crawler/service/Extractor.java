package com.example.spider.crawler.service;

import com.example.spider.crawler.domain.Webpage;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;


@Slf4j
@Component
public class Extractor {

  public Webpage crawl(String url) { //collect to a list and return

    Webpage.WebpageBuilder response = Webpage.builder();
    try {
      Document doc = Jsoup.connect(url).get();
      Elements element = doc.select("p"); //gets paragraphs

      Element image = doc.select("img[src~=(?i)\\.(png|jpe?g|gif)]").first();
      String imageUrl = image.absUrl("src");

      String title = doc.title();

      String keywords = null;
      try {
        keywords = doc.select("meta[name=keywords]").first().attr("content");
        log.info("Meta keyword : " + keywords);
      }catch(Exception e) {
        log.info("No keywords found, using page title");
      }

      String keyword = "";
      if(keywords != null){
        keyword = keywords;
      }
      else {
        keyword = title;
      }

      StringBuilder data = new StringBuilder();
      for (Element t : element) {
        data.append(t.text());
      }

      response
        .searchedUrl(url)
        .data(data.toString())
        .adUrl(imageUrl)
        .keyword(keyword);

    } catch (Exception ex) {
      log.info(ex.getMessage());

    }

    return response.build();

  }
}