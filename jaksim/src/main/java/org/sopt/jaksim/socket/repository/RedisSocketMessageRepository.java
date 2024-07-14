//package org.sopt.jaksim.socket.repository;
//
//import org.sopt.jaksim.socket.message.Message;
//import org.springframework.data.repository.CrudRepository;
//
//import java.util.List;
//
//public interface RedisSocketMessageRepository extends CrudRepository<Message, Long> {
//    List<Message> findAllByRoom(String room);
//    boolean existsMessageByReceiverId(String receiverId);
//}
