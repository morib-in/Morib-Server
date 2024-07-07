package org.sopt.jaksim.category.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.jaksim.global.common.BaseTimeEntity;

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
    private Long todoId;
}
