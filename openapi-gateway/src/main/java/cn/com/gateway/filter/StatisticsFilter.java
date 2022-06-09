package cn.com.gateway.filter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.LinkedHashSet;

@Component
public class StatisticsFilter implements GlobalFilter, Ordered {

    private static final Logger log = LoggerFactory.getLogger(StatisticsFilter.class);
    private static final String START_TIME = "startTime";
    private static Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss.SSS").create();
    private static final String GATEWAY_ORIGNIAL_REQUEST_URL = "org.springframework.cloud.gateway.support.ServerWebExchangeUtils.gatewayOriginalRequestUrl";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put(START_TIME, System.currentTimeMillis());
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            Long startTime = exchange.getAttribute(START_TIME);
            if (startTime != null) {
                long duration = (System.currentTimeMillis() - startTime);
                LogEvent event = new LogEvent("openapi-gateway-statistics");
                String path = exchange.getRequest().getPath().toString();
                if (exchange.getAttributes().containsKey(GATEWAY_ORIGNIAL_REQUEST_URL)) {
                    LinkedHashSet<URI> urlSet = (LinkedHashSet<URI>) exchange.getAttributes().get(GATEWAY_ORIGNIAL_REQUEST_URL);
                    for (URI uri : urlSet) {
                        if (uri.toString().startsWith("lb://")) {
                            String service = uri.getHost();
                            event.setService(service);
                            break;
                        }
                    }
                }
                event.setStatusCode(exchange.getResponse().getRawStatusCode().toString());
                event.setDuration(duration);
                event.setUrl(path);
                log.warn(event.toString());
            }
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    class LogEvent {

        private String url;
        private String service;
        private long duration;
        private String statusCode;
        private String indexPrefix;

        public LogEvent(String indexPrefix) {
            this.indexPrefix = indexPrefix;
        }

        public String getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(String statusCode) {
            this.statusCode = statusCode;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getService() {
            return service;
        }

        public void setService(String service) {
            this.service = service;
        }

        public long getDuration() {
            return duration;
        }

        public void setDuration(long duration) {
            this.duration = duration;
        }

        @Override
        public String toString() {
            return gson.toJson(this);
        }
    }
}
