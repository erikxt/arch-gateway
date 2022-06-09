package cn.com.gateway.remote;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "cbim-gateway-admin", fallbackFactory = GatewayAdminFallbackFactory.class)
public interface GatewayAdminClient {
}
