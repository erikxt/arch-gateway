//package cn.com.cbim.gateway.admin;
//
//import cn.com.cbim.gateway.admin.message.AppEvent;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.bus.ServiceMatcher;
//import org.springframework.context.ApplicationContext;
//import org.springframework.context.ApplicationEventPublisher;
//
//@SpringBootTest(classes = AdminApp.class)
//public class MessageTest {
//
//    @Autowired
//    ApplicationEventPublisher applicationEventPublisher;
//    @Autowired
//    ServiceMatcher busServiceMatcher;
//    ApplicationContext context;
//
//    @org.junit.jupiter.api.Test
//    void publisher() {
//        applicationEventPublisher.publishEvent(new AppEvent(this, context.getId(), null, "test message"));
//    }
//}
