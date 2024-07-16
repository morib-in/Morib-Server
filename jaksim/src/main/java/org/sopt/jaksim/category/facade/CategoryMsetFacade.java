package org.sopt.jaksim.category.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.category.dto.CategoryMsetLinkResponse;
import org.sopt.jaksim.category.service.CategoryService;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.domain.Mset;
import org.sopt.jaksim.mset.service.CategoryMsetService;
import org.sopt.jaksim.mset.service.MsetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryMsetFacade {
    private final CategoryService categoryService;
    private final MsetService msetService;
    private final CategoryMsetService categoryMsetService;

    public CategoryMsetLinkResponse getFromOtherCategory(Long categoryId) {
        Category category = categoryService.getCategoryById(categoryId);
        List<Mset> msetList = new ArrayList<>();
        List<CategoryMset> categoryMsetList = categoryMsetService.getByCategoryId(categoryId);
        for (CategoryMset categoryMset : categoryMsetList) {
            msetList.add(msetService.getMsetById(categoryMset.getMsetId()));
        }
        return CategoryMsetLinkResponse.of(category, msetList);
    }

    public void deleteCategoryMsetAndMsets(Long categoryId) {
        List<Long> msetIdList = categoryMsetService.deleteAndGetMsetIdList(categoryId);
        deleteMsetById(msetIdList);
    }

    public void deleteMsetById(List<Long> msetIdList) {
        msetService.delete(msetIdList);
    }

}
