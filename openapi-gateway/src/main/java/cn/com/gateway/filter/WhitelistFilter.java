package cn.com.gateway.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 白名单过滤器
 */
@Component
public class WhitelistFilter implements GlobalFilter, Ordered {
    private static final Logger log = LoggerFactory.getLogger(WhitelistFilter.class);

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//        log.info("前置全局过滤器");
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
//            log.info("后置全局过滤器");
        }));
    }

    /**
     * 值越小，优先级越高
     */
    @Override
    public int getOrder() {
        return 1;
    }


}
