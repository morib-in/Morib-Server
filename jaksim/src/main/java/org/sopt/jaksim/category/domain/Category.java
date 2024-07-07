package org.sopt.jaksim.category.domain;

import jakarta.persistence.*;
import lombok.*;
import org.sopt.jaksim.global.common.BaseTimeEntity;
import org.sopt.jaksim.user.domain.Platform;

import java.time.LocalDate;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
@Getter
@Setter
@Table(name = "categories")
@Entity
public class Category extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long userId;
    @Column(nullable = false)
    private String startDate;
    private String endDate;

    public static Category create(String name, Long userId, String startDate, String endDate) {
        return Category.builder()
                .name(name)
                .userId(userId)
                .startDate(startDate)
                .endDate(endDate)
                .build();
    }
}
