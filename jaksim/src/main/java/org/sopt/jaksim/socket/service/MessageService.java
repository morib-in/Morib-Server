//package org.sopt.jaksim.socket.service;
//
//import lombok.RequiredArgsConstructor;
//import org.sopt.jaksim.socket.message.Message;
//import org.sopt.jaksim.socket.repository.RedisSocketMessageRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor(onConstructor = @__(@Autowired))
//public class MessageService {
//
//    private final RedisSocketMessageRepository redisSocketMessageRepository;
//
//    public List<Message> getMessage(String room) {
//        return redisSocketMessageRepository.findAllByRoom(room);
//    }
//
//    public Message saveMessage(Message message) {
//        return redisSocketMessageRepository.save(message);
//    }
//
//    public boolean isClientExist(String sessionId) {
//        return redisSocketMessageRepository.existsMessageByReceiverId(sessionId);
//    }
//
//}
