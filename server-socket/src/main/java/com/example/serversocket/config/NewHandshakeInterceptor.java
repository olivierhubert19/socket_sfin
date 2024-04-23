package com.example.serversocket.config;

import com.example.serversocket.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Map;
@Configuration
public class NewHandshakeInterceptor implements HandshakeInterceptor {

        @Autowired
        private JwtService jwtService;

        public NewHandshakeInterceptor(JwtService jwtService){
            this.jwtService=jwtService;
        }

        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
            String cookieHeader = request.getHeaders().getFirst("cookie");
            System.out.println("Header "+cookieHeader);
            String[] token = cookieHeader.split("=");
            // Trả về true để cho phép handshake
            System.out.println(jwtService.validateJwtToken(token[1]));
            return !jwtService.validateJwtToken(token[1]);
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                WebSocketHandler wsHandler, Exception exception) {
            System.out.println(request.getURI());
        }
}
