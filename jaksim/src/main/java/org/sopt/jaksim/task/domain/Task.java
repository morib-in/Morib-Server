package org.sopt.jaksim.task.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.jaksim.global.common.BaseTimeEntity;
import org.sopt.jaksim.user.domain.Platform;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Entity
public class Task extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Boolean isComplete;
    @Column(nullable = false)
    private LocalDate startDate;
    private LocalDate endDate;

    public static Task create(String name, LocalDate startDate, LocalDate endDate) {
        return Task.builder()
                .name(name)
                .isComplete(false)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
