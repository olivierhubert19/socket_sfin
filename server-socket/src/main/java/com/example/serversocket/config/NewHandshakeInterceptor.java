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

        @Override
        public boolean beforeHandshake(ServerHttpRequest request, ServerHttpResponse response,
                                       WebSocketHandler wsHandler, Map<String, Object> attributes) throws Exception {
            // Kiểm tra và xử lý thông tin của request trước khi thiết lập kết nối
            // Ví dụ: Kiểm tra header, parameter, ...
            String cookieHeader = request.getHeaders().getFirst("cookie");
            System.out.println("Header "+cookieHeader);
            String[] token = cookieHeader.split("=");
            // Trả về true để cho phép handshake
            return jwtService.validateJwtToken(token[1]);
        }

        @Override
        public void afterHandshake(ServerHttpRequest request, ServerHttpResponse response,
                WebSocketHandler wsHandler, Exception exception) {
            String token = request.getHeaders().getFirst("Authorization");
            // Xử lý sau khi handshake hoàn thành (nếu cần)
        }
}
