package org.sopt.jaksim.category.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.jaksim.global.common.BaseTimeEntity;
import org.sopt.jaksim.task.domain.Task;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "category_task")
@Entity
public class CategoryTask extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long taskId;
    @Column(nullable = false)
    private Long categoryId;

    public static CategoryTask create(Long categoryId, Long taskId) {
        return CategoryTask.builder()
                .categoryId(categoryId)
                .taskId(taskId)
                .build();
    }
}


