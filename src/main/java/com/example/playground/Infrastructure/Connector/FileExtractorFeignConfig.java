package com.example.playground.Infrastructure.Connector;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class FileExtractorFeignConfig {
    @Value(value = "${app.FileExtractor.client.url}")
    protected String url;
    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")
                .create();
    }
    @Bean
    public FileExtractorFeignClient fileExtractorFeignClient(){
        log.info("[FileExtractorFeignConfig] Creating fileExtractor....");
        return Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder(gson()))
                .decoder(new GsonDecoder(gson()))
                .logger(new Slf4jLogger(FileExtractorFeignClient.class))
                .logLevel(Logger.Level.FULL)
                .target(FileExtractorFeignClient.class, url);
    }
}
