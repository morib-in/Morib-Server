package org.sopt.jaksim.category.repository;

import org.sopt.jaksim.category.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query("select e from Category e where " +
            "e.userId = :userId " +
            "and e.startDate between :idxStartDate and :idxEndDate " +
            "or e.endDate between :idxStartDate and :idxEndDate " +
            "or (e.startDate <= :idxStartDate and e.endDate >= :idxEndDate)")
    List<Category> findByUserIdWithRange(Long userId, LocalDate idxStartDate, LocalDate idxEndDate);
}
