package com.hzy.app.gateway.filter;

import com.hzy.app.gateway.util.AppJwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.List;

/**
 * @title: AuthorizeFilter 认证过滤器
 * @Author zxwyhzy
 * @Date: 2023/9/6 21:49
 * @Version 1.0
 */

@Component
@Slf4j
public class AuthorizeFilter implements Ordered, GlobalFilter {

    /**
     * 过滤方法
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 1.获取request和response对象
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpResponse response = exchange.getResponse();

        if (1 == 1){
            return chain.filter(exchange);
        }

        // 2.判断是否是登录
        if (request.getURI().getPath().contains("/login")) {
            // 放行
            return chain.filter(exchange);
        }

        // 3.获取token
        String token = request.getHeaders().getFirst("token");

        // 4.判断token是否存在
        if (StringUtils.isNotBlank(token)) {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 结束请求
            return response.setComplete();
        }
        // 5.判断token是否有效
        try {
            // 解析有可能失败
            Claims claimsBody = AppJwtUtil.getClaimsBody(token);
            // 5.1 判断是否过期
            int result = AppJwtUtil.verifyToken(claimsBody);
            if (result == 1 || result == 2) {
                response.setStatusCode(HttpStatus.UNAUTHORIZED);
                // 结束请求
                return response.setComplete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            // 结束请求
            return response.setComplete();
        }
        // 6.放行

        return chain.filter(exchange);
    }

    /**
     * 优先级设置
     *
     * @return 优先级 值越小优先级越高
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
