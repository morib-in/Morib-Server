package org.sopt.jaksim.mset.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.CategoryTask;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.repository.CategoryMsetRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryMsetService {
    private final CategoryMsetRepository categoryMsetRepository;

    public List<CategoryMset> getByCategoryId(Long categoryId) {
        return categoryMsetRepository.findByCategoryId(categoryId);
    }

    public List<Long> deleteAndGetMsetIdList(Long categoryId) {
        List<CategoryMset> categoryMsetList = getByCategoryId(categoryId);
        List<Long> msetIdList = categoryMsetList.stream().map(CategoryMset::getMsetId).collect(Collectors.toList());
        if (categoryMsetList.isEmpty()) { // 카테고리에 등록된 모립세트가 없는 경우
            return null;
        }
        categoryMsetRepository.deleteAll(categoryMsetList);
        return msetIdList;
    }

}
