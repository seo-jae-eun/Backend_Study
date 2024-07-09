package com.example.apigateway.filters;

import com.example.apigateway.utils.JwtUtil;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;

@Component
public class JwtFilter extends AbstractGatewayFilterFactory<JwtFilter.Config> {
    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        super(Config.class);
        this.jwtUtil = jwtUtil;
    }

    public static class Config {

    }
    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain)-> {
            String authorization = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if(authorization == null || !authorization.startsWith("Bearer ")) {
                System.out.println("Bearer is null");
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            String token = authorization.split(" ")[1];
            if(jwtUtil.isExpired(token)) {
                System.out.println("Expired token");
                ServerHttpResponse response = exchange.getResponse();
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                return response.setComplete();
            }

            exchange.getRequest().mutate()
                    .header("X-User-Idx", ""+jwtUtil.getIdx(token))
                    .header("X-User-Email", ""+jwtUtil.getUsername(token))
                    .header("X-User-Role", ""+jwtUtil.getRole(token))
                    .build();

            return chain.filter(exchange);
        });
    }
}
