package com.obf.movie.config;

import com.google.common.collect.ImmutableList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfiguration {
    private static final Logger log = LoggerFactory.getLogger(RestTemplateConfiguration.class);
    private static final int READ_TIME_OUT = 20000;
    private static final int CONNECTION_TIME_OUT = 20000;

    private final ApplicationProperties applicationProperties;

    public RestTemplateConfiguration(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() {
        HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setReadTimeout(READ_TIME_OUT);
        requestFactory.setConnectTimeout(CONNECTION_TIME_OUT);
        return requestFactory;
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory());
        restTemplate.setInterceptors(ImmutableList.of(authInterceptor()));
        restTemplate.getMessageConverters().add(messageConverter());
        log.info("Initiated RestTemplate in {}", "");
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestInterceptor authInterceptor() {
        return new RequestInterceptor(applicationProperties);
    }

    @Bean
    public HttpMessageConverter messageConverter() {
        return new MappingJackson2HttpMessageConverter();
    }
}
