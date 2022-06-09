package cn.com.gateway.remote;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class GatewayAdminFallbackFactory implements FallbackFactory<GatewayAdminClient> {
    private static final Logger log = LoggerFactory.getLogger(GatewayAdminFallbackFactory.class);

    @Override
    public GatewayAdminClient create(Throwable cause) {
        log.error("异常回退", cause);
        return new GatewayAdminClient() {

        };
    }
}
