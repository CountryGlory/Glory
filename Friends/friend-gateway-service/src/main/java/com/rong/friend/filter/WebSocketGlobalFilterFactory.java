package com.rong.friend.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;


@Component
public class WebSocketGatewayFilterFactory extends AbstractGatewayFilterFactory<WebSocketGatewayFilterFactory.Config> {

    private static final String AUTHORIZATION_HEADER = "Authorization";

    private static final String BEARER_TOKEN_TYPE = "bearer";

    private static final Logger log= LoggerFactory.getLogger(WebSocketGatewayFilterFactory.class);

    public WebSocketGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return this::filter;
    }

    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String token=exchange.getRequest().getHeaders().getFirst("Sec-Websocket-Accept");
        log.info("send {} request to {}",exchange.getRequest().getMethod().toString(),exchange.getRequest().getURI().getPath());
        if((StringUtils.isEmpty(token) || token.equals("undefined"))){
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            log.error("token is Empty");
            return exchange.getResponse().setComplete();
        }else{
            ServerHttpRequest host = exchange.getRequest().mutate().header(AUTHORIZATION_HEADER, BEARER_TOKEN_TYPE + token).build();
            ServerWebExchange build = exchange.mutate().request(host).build();
            return chain.filter(build);
        }
    }

    public static class Config {
    }

    @Override
    public String name() {
        return "WebSocketGatewayFilter";
    }

}
