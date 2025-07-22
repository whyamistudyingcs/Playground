package com.example.playground.Domain.Config;

import java.util.Objects;
import java.util.UUID;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.AuthorizedClientServiceOAuth2AuthorizedClientManager;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import feign.RequestInterceptor;

@Configuration
public class GenericFeignClientConfig {
    @Bean
    public RequestInterceptor oauth2FeignRequestInterceptor(
        AuthorizedClientServiceOAuth2AuthorizedClientManager authorizedClientManager) {
        return requestTemplate -> { 
            OAuth2AuthorizeRequest authorizeRequest = OAuth2AuthorizeRequest
                .withClientRegistrationId("my-client") // Use the registration ID of your OAuth2 client
                .principal("my-principal") // Use the principal name or object as needed
                .build();
            OAuth2AuthorizedClient authorizedClient = authorizedClientManager.authorize(authorizeRequest);
            String token = Objects.requireNonNull(authorizedClient)
                .getAccessToken()
                .getTokenValue();
            requestTemplate.header("Authorization", "Bearer " + token);
        };
    }
    
    @Bean
    public RequestInterceptor dummyAuthInterceptor() {
        return requestTemplate -> {
            requestTemplate.header("Authorization", UUID.randomUUID().toString());
        };
    }

    @Bean
    public Gson gson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'")
                .create();
    }
}
