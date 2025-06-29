package com.example.playground.Domain.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

/*https://blog.devops.dev/spring-cloud-gateway-oauth2-security-with-keycloak-jwt-tokens-and-securing-it-with-https-ssl-2166d8009531*/
/*https://medium.com/@tobintom/documenting-oauth2-secured-spring-boot-microservices-with-swagger-3-openapi-3-0-166618ea1f5*/
/*https://igventurelli.io/implementing-oauth2-in-spring-boot-a-step-by-step-guide/*/
/*https://github.com/georgeberar/medium/blob/main/resource-server-oauth2-google/src/main/java/com/example/resourceserver/configuration/SecurityConfiguration.java*/

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(AbstractHttpConfigurer::disable)
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html", "/api/health").permitAll()
                .anyRequest().permitAll() // temporarily disable the Oauth2
            )
            .headers(httpSecurityHeadersConfigurer -> {
                // allow rendering within an iframe for h2-console (https://stackoverflow.com/questions/53395200/h2-console-is-not-showing-in-browser)
                httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin);
            })
            .oauth2ResourceServer(spec -> spec.jwt(Customizer.withDefaults()));
        return http.build();
    }
}
