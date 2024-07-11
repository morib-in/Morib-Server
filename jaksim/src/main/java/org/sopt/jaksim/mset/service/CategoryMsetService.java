package org.sopt.jaksim.mset.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.CategoryTask;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.repository.CategoryMsetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryMsetService {
    private final CategoryMsetRepository categoryMsetRepository;
    public List<CategoryMset> getByCategoryId(Long categoryId) {
        return categoryMsetRepository.findByCategoryId(categoryId);
    }
}
