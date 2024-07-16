package org.sopt.jaksim.category.repository;

import org.sopt.jaksim.category.domain.CategoryTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryTaskRepository extends JpaRepository<CategoryTask, Long> {
    CategoryTask findByTaskId(Long taskId);
    List<CategoryTask> findByCategoryId(Long categoryId);
}
