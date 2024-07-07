package org.sopt.jaksim.task.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.jaksim.global.common.BaseTimeEntity;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "todo_tasks")
@Entity
public class TodoTask extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private Long taskId;
    @Column(nullable = false)
    private Long todoId;
}