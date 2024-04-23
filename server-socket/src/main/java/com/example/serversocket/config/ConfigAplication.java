package com.example.serversocket.config;

import com.example.serversocket.service.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.example.serversocket.service")
public class ConfigAplication {
    @Bean(name = "jwtService")
    public JwtService jwtService(){
        return new JwtService();
    }

}
