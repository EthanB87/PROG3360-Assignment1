package org.example.productservice.Config;

import io.getunleash.DefaultUnleash;
import io.getunleash.Unleash;
import io.getunleash.util.UnleashConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UnleashConfiguration {

    @Bean
    public Unleash unleash() {
        String apiUrl = System.getenv("UNLEASH_API_URL");
        if (apiUrl == null || apiUrl.isEmpty()) {
            apiUrl = "http://unleash-server:4242/api";
        }

        String token = System.getenv("UNLEASH_API_TOKEN");
        if (token == null || token.isEmpty()) {
            token = "*:development.207c60daac5adad5af6a049179ac7b4cc4df262257702fc3333ef4d6";
        }

        UnleashConfig config = UnleashConfig.builder()
                .appName("product-service")
                .instanceId(System.getenv("HOSTNAME") != null ? System.getenv("HOSTNAME") : "product-service-instance")
                .unleashAPI(apiUrl)
                .apiKey(token)
                .build();

        return new DefaultUnleash(config);
    }
}