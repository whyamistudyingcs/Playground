package com.example.playground.Domain.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.OAuthFlow;
import io.swagger.v3.oas.annotations.security.OAuthFlows;
import io.swagger.v3.oas.annotations.security.OAuthScope;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    servers = {@Server(url = "http://localhost:8081")},
    info = @Info(
        title = "Playground service APIs",
        description = "This lists all the playground service api secured by OAuth2",
        version = "v1.0"
    )
)
@SecurityScheme(
    name = "security_auth",
    type = SecuritySchemeType.OAUTH2,
    flows = @OAuthFlows(
        implicit = @OAuthFlow(
            authorizationUrl = "${openapi.oAuthFlow.authorizationUrl}",
            scopes = {
                @OAuthScope(name = "openid", description = "Associate you with your personal info"),
                @OAuthScope(name = "profile", description = "See your personal info, including any personal info you've made publicly available"),
                @OAuthScope(name = "email", description = "See your primary Google Account email address")
            }
        )
    )
)
//@SecurityScheme(
//    name = "security_auth",
//    type = SecuritySchemeType.OAUTH2,
//    flows = @OAuthFlows(
//        authorizationCode = @OAuthFlow(
//            authorizationUrl = "${openapi.oAuthFlow.authorizationUrl}",
//            tokenUrl = "${openapi.oAuthFlow.tokenUrl}",
//            scopes = {
//                @OAuthScope(name = "openid", description = "Associate you with your personal info"),
//                @OAuthScope(name = "profile", description = "See your personal info, including any personal info you've made publicly available"),
//                @OAuthScope(name = "email", description = "See your primary Google Account email address")
//            }
//        )
//    )
//)
public class OpenAPI3Configuration {
}
