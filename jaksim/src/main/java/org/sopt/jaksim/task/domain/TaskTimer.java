package org.sopt.jaksim.task.domain;


import jakarta.persistence.*;
import lombok.*;
import org.sopt.jaksim.global.common.BaseTimeEntity;
import org.sopt.jaksim.task.repository.TaskTimerRepository;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class TaskTimer extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private Long taskId;
    @Column(nullable = false)
    private LocalDate targetDate;
    private int targetTime;

    public static TaskTimer create(Long userId, Long taskId) {
        return TaskTimer.builder()
                .userId(userId)
                .taskId(taskId)
                .targetDate(LocalDate.now())
                .targetTime(0)
                .build();
    }
}
