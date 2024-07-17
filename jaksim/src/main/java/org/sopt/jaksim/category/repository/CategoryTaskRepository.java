package org.sopt.jaksim.category.repository;

import org.sopt.jaksim.category.domain.CategoryTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CategoryTaskRepository extends JpaRepository<CategoryTask, Long> {
    Optional<CategoryTask> findByTaskId(Long taskId);
    List<CategoryTask> findByCategoryId(Long categoryId);
}
