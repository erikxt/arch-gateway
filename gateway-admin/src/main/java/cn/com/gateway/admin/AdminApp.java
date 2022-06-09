package cn.com.gateway.admin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.core.env.Environment;

@EnableAdminServer
@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(value = "cn.com.gateway.admin.mapper")
public class AdminApp {
    private static final Logger log = LoggerFactory.getLogger(AdminApp.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(AdminApp.class);
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
