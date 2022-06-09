//package cn.com.cbim.gateway.message;
//
//import org.springframework.cloud.bus.event.Destination;
//import org.springframework.cloud.bus.event.RemoteApplicationEvent;
//
//public class AppTestEvent extends RemoteApplicationEvent {
//
//    private String message;
//
//    public AppTestEvent(String message) {
//        this.message = message;
//    }
//
//    public AppTestEvent(Object source, String originService, Destination destination, String message) {
//        super(source, originService, destination);
//        this.message = message;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//}
