package com.example.spider.crawler.domain;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Webpage
{
  private String searchedUrl;
  private String data;
  private String adUrl;
  private String keyword;

}
