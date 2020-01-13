package com.example.spider.crawler.config;

import io.micrometer.core.aop.TimedAspect;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MicrometerConfig {

//  @Bean
//  public TimedAspect timedAspect(MeterRegistry meterRegistry) {
//    return new TimedAspect(meterRegistry);
//  }
//
//  @Bean
//  public Counter numberOfSuccessfulSearches(MeterRegistry meterRegistry) {
//    return meterRegistry.counter(SearchDao.class.getPackage().getName() + ".numberOfSuccessfulSearches");
//  }
//
//  @Bean
//  public Counter numberOfFailedSearches(MeterRegistry meterRegistry) {
//    return meterRegistry.counter(SearchDao.class.getPackage().getName() + ".numberOfFailedSearches");
//  }
}


