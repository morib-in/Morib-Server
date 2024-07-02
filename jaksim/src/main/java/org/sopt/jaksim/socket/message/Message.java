package org.sopt.jaksim.socket.message;

import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

@Builder
@Getter
@Setter
@Data
@RedisHash(value = "socketMessage", timeToLive = 60 * 60 * 24 * 1000L * 14)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor
public class Message {
    @Id
    private String id;

    @Column
    @Indexed
    private String receiverId;

    @Column
    @Enumerated(EnumType.STRING)
    private MessageType messageType;

    @Column
    private String room;

    @Column
    private String username;

    @Column
    private String message;

}
