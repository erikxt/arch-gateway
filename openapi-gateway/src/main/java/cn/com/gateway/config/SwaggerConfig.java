package cn.com.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger聚合API文档
 */
@Primary
@Component
public class SwaggerConfig implements SwaggerResourcesProvider {

    public static final String API_URI = "/v2/api-docs";

    @Value("${spring.application.name}")
    private String self;

    private final DiscoveryClient discoveryClient;

    public SwaggerConfig(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @Override
    public List<SwaggerResource> get() {
        List<SwaggerResource> resources = new ArrayList<>();
        discoveryClient.getServices().stream()
                // 排除自身
                .filter(s -> !s.equals(self))
                .forEach(name -> resources.add(swaggerResource(name, "/" + name + API_URI)));
        return resources;
    }

    private SwaggerResource swaggerResource(String name, String location) {
        SwaggerResource swaggerResource = new SwaggerResource();
        swaggerResource.setName(name);
        swaggerResource.setLocation(location);
        swaggerResource.setSwaggerVersion(DocumentationType.SWAGGER_2.getVersion());
        return swaggerResource;
    }
}
