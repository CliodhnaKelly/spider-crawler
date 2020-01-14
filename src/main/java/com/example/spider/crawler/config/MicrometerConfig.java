package com.example.spider.crawler.config;

import com.example.spider.crawler.controller.Controller;
import com.example.spider.crawler.dao.CrawlerDao;
import com.example.spider.crawler.service.Extractor;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerConfig {

  @Bean
  public Counter errorCrawlingWebpage(MeterRegistry meterRegistry) {
    return meterRegistry.counter(Extractor.class.getPackage().getName() + ".numberOfErrorsCrawlingWebpage");
  }

  @Bean
  public Counter errorAddingToDatabase(MeterRegistry meterRegistry) {
    return meterRegistry.counter(CrawlerDao.class.getPackage().getName() + ".numberOfErrorsAddingToDatabase");
  }

  @Bean
  public Counter emptyDataFoundInWebpage(MeterRegistry meterRegistry) {
    return meterRegistry.counter(Extractor.class.getPackage().getName() + ".numberOfEmptyDataFoundInWebpage");
  }

  @Bean
  public Counter emptyImageFoundInWebpage(MeterRegistry meterRegistry) {
    return meterRegistry.counter(Extractor.class.getPackage().getName() + ".numberOfEmptyImagesFoundInWebpage");
  }

  @Bean
  public Counter numberOfPagesCrawled(MeterRegistry meterRegistry) {
    return meterRegistry.counter(Controller.class.getPackage().getName() + ".numberOfPagesCrawled");
  }
}


