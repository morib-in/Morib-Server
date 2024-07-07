package org.sopt.jaksim.task.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import java.time.LocalDate;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
@RedisHash(value = "user_timer", timeToLive = 60 * 60 * 24 * 1000L)
public class UserTimer {
    @Id
    private Long id;
    private Long userId;
    @Indexed
    private String targetDate;
    private int targetTime;

    public static UserTimer create(Long userId, String targetDate) {
        return UserTimer.builder()
                .userId(userId)
                .targetDate(targetDate)
                .targetTime(0)
                .build();
    }
}
