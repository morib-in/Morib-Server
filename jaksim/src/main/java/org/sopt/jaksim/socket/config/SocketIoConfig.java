//package org.sopt.jaksim.socket.config;
//
//import com.corundumstudio.socketio.SocketIOServer;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//public class SocketIoConfig {
//
//    @Value("${socketio.server.hostname}")
//    private String hostname;
//
//    @Value("${socketio.server.port}")
//    private int port;
//
//    @Bean
//    public SocketIOServer socketIoServer() {
//        com.corundumstudio.socketio.Configuration config = new com.corundumstudio.socketio.Configuration();
//        config.setHostname(hostname);
//        config.setPort(port);
//        return new SocketIOServer(config);
//    }
//
//}