package org.sopt.jaksim.mset.repository;

import org.sopt.jaksim.mset.domain.CategoryMset;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryMsetRepository extends JpaRepository<CategoryMset, Long> {
    List<CategoryMset> findByCategoryId(Long categoryId);
}
