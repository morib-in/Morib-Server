package org.sopt.jaksim.task.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@RedisHash(value = "user_timer", timeToLive = 60 * 60 * 24 * 1000L)
public class UserTimer {
    @Id
    private Long id;
    @Indexed
    private Long userId;
    private String targetDate;
    private int targetTime;
}
