package com.example.playground.Domain.Config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import com.example.playground.Infrastructure.Connector.testFeignClient.TestFeignClient;
import com.google.gson.Gson;

import feign.Feign;
import feign.Logger;
import feign.RequestInterceptor;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class TestFeignConfig {
    @Value(value = "${test.url}")
    protected String url;
    @Bean
    public TestFeignClient testFeignClient(
        Gson gson,
        @Qualifier("dummyAuthInterceptor") RequestInterceptor requestInterceptor
    ){
        log.info("[TestFeignConfig] Creating testFeignClient....");
        return Feign.builder()
                .retryer(new feign.Retryer.Default(1000, 5000, 3))
                .client(new OkHttpClient())
                .encoder(new GsonEncoder(gson))
                .decoder(new GsonDecoder(gson))
                .logger(new Slf4jLogger(TestFeignClient.class))
                .logLevel(Logger.Level.FULL)
                .requestInterceptor(requestInterceptor)
                .target(TestFeignClient.class, url);
    }
}
