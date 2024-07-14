//package org.sopt.jaksim.socket.service;
//
//import com.corundumstudio.socketio.SocketIOClient;
//import lombok.RequiredArgsConstructor;
//import org.sopt.jaksim.socket.message.Message;
//import org.sopt.jaksim.socket.message.MessageType;
//import org.springframework.stereotype.Service;
//
//@Service
//@RequiredArgsConstructor
//public class SocketService {
//
//    private final MessageService messageService;
//
//    public void sendSocketmessage(SocketIOClient senderClient, Message message, String room) {
//        for (
//                SocketIOClient client: senderClient.getNamespace().getRoomOperations(room).getClients()
//        ) {
//            if (message.getReceiverId().equals(client.getSessionId().toString())) {
//                client.sendEvent("read_message", message);
//            }
//        }
//
//    }
//
//    public void saveMessage(SocketIOClient senderClient, Message message) {
//        Message storedMessage = messageService.saveMessage(
//                Message.builder()
//                        .receiverId(message.getReceiverId())
//                        .messageType(MessageType.CLIENT)
//                        .message(message.getMessage())
//                        .room(message.getRoom())
//                        .username(message.getUsername())
//                        .build()
//        );
//        sendSocketmessage(senderClient, storedMessage, message.getRoom());
//    }
//
//    public void saveInfoMessage(SocketIOClient senderClient, String message, String room) {
//        Message storedMessage = messageService.saveMessage(
//                Message.builder()
//                        .receiverId(senderClient.getSessionId().toString())
//                        .messageType(MessageType.SERVER)
//                        .message(message)
//                        .room(room)
//                        .build()
//        );
//
//        sendSocketmessage(senderClient, storedMessage, room);
//    }
//
//    public boolean isClientExist(String sessionId) {
//        return messageService.isClientExist(sessionId);
//    }
//
//}
