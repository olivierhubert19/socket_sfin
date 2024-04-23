package com.example.serversocket.config;

import com.example.serversocket.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.serversocket")
public class ConfigAplication {
    @Bean
    public JwtService jwtService(){
        return new JwtService();
    }

}
