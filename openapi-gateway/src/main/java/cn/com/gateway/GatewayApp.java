package cn.com.gateway;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.core.env.Environment;

@EnableFeignClients
@SpringBootApplication
public class GatewayApp {
    private static final Logger log = LoggerFactory.getLogger(GatewayApp.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(GatewayApp.class);
        Environment env = app.run(args).getEnvironment();
        String serverPort = env.getProperty("server.port");
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}/\n\t" +
                        "Profile(s): \t{}\n----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                serverPort,
                env.getActiveProfiles());
    }
}
