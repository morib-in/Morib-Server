package org.sopt.jaksim.socket.config;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.corundumstudio.socketio.listener.DisconnectListener;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.socket.message.Message;
import org.sopt.jaksim.socket.service.SocketService;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;
import static org.sopt.jaksim.global.common.Constants.SOCKET_WELCOME_MESSAGE;
import static org.sopt.jaksim.global.common.Constants.SOCKET_DISCONNECT_MESSAGE;
@Slf4j
@Component
public class SocketModule {

    private final SocketIOServer server;

    private final SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(this.onConnected());
        server.addDisconnectListener(this.onDisconnected());
        server.addEventListener("send_message", Message.class, onChatReceived());
    }

    private DataListener<Message> onChatReceived() {
        return (senderClient, data, ackSender) -> {
//            log.info("client:" + senderClient.toString());
            log.info("data:" + data.toString());
            socketService.saveMessage(senderClient, data);
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            if (!socketService.isClientExist(client.getSessionId().toString())) {
                var params = client.getHandshakeData().getUrlParams();
                String room = params.get("room").stream().collect(Collectors.joining());
                String username = params.get("username").stream().collect(Collectors.joining());
                client.joinRoom(room);
                socketService.saveInfoMessage(client, String.format(SOCKET_WELCOME_MESSAGE, username), room);
                log.info("connection-------");
                log.info("Socket ID[{}] - room[{}] - username [{}]  Connected to chat module through", client.getSessionId().toString(), room, username);
            }
        };
    }

    private DisconnectListener onDisconnected() {
        return client -> {
            var params = client.getHandshakeData().getUrlParams();
            String room = params.get("room").stream().collect(Collectors.joining());
            String username = params.get("username").stream().collect(Collectors.joining());
            socketService.saveInfoMessage(client, String.format(SOCKET_DISCONNECT_MESSAGE, username), room);
            log.info("Socket ID[{}] - room[{}] - username [{}]  discnnected to chat module through", client.getSessionId().toString(), room, username);
        };
    }

}