package com.eme22.hospitalwebserver;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.ConfigurableConversionService;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
@Configuration
public class LocalDateConfiguration implements RepositoryRestConfigurer {
                                                                                                
  @Override                                                                                   
  public void configureConversionService(ConfigurableConversionService conversionService) {
    conversionService.addConverter((Converter<String, LocalDate>) source -> {
      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
      return LocalDate.parse(source, formatter);
    });
    conversionService.addConverter((Converter<LocalDate, String>) LocalDate::toString);
  }                                                                                           
                                                                                                
}     