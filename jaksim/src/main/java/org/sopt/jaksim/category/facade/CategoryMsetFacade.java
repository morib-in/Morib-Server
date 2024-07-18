package org.sopt.jaksim.category.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.category.dto.CategoryMsetLinkResponse;
import org.sopt.jaksim.category.service.CategoryService;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.domain.Mset;
import org.sopt.jaksim.mset.dto.MsetOfTask;
import org.sopt.jaksim.mset.service.CategoryMsetService;
import org.sopt.jaksim.mset.service.MsetService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
        if (msetIdList != null) { // 카테고리에 등록된 모립세트가 있을 경우에만 delete
            deleteMsetById(msetIdList);
        }
    }

    public void deleteMsetById(List<Long> msetIdList) {
        msetService.delete(msetIdList);
    }

    public List<MsetOfTask> getFromCategory(Long categoryId) {
        return getFromOtherCategory(categoryId).msetList().stream().map(
                mset -> MsetOfTask.of(mset.getId(), mset.getName(), mset.getUrl())
        ).collect(Collectors.toList());

    }

}
