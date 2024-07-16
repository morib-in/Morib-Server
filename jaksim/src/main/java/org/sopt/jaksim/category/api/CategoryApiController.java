package org.sopt.jaksim.category.api;


import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.category.dto.CategoryCheckResponse;
import org.sopt.jaksim.category.dto.CategoryCreateRequest;
import org.sopt.jaksim.category.dto.FilteredResourceResponse;
import org.sopt.jaksim.category.facade.CategoryMsetFacade;
import org.sopt.jaksim.category.service.CategoryService;
import org.sopt.jaksim.category.facade.CategoryTaskFacade;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.message.SuccessMessage;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryApiController implements CategoryApi {
    private final CategoryService categoryService;
    private final CategoryTaskFacade categoryTaskFacade;
    private final CategoryMsetFacade categoryMsetFacade;

    @PostMapping("/categories")
    @Override
    public ResponseEntity<BaseResponse<?>> create(@RequestBody CategoryCreateRequest request) {
        categoryService.create(request);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

    @GetMapping("/resources")
    @Override
    public ResponseEntity<BaseResponse<?>> retrieve(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<FilteredResourceResponse> response = categoryTaskFacade.getAllResources(startDate, endDate);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
    }

    @GetMapping("/categories")
    @Override
    public ResponseEntity<BaseResponse<?>> getCategoriesByUserId() {
        List<CategoryCheckResponse> categories = categoryService.getCategoriesByUserId(); // categoryRepository를 통해 데이터베이스에서 특정 사용자의 카테고리를 조회
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, categories); // 성공 응답 반환
    }

    @DeleteMapping("/categories/{categoryId}")
    public ResponseEntity<BaseResponse<?>> delete(@PathVariable("categoryId") Long categoryId) {
        categoryTaskFacade.deleteCategoryTaskAndTasks(categoryId);
        categoryMsetFacade.deleteCategoryMsetAndMsets(categoryId);
        categoryTaskFacade.delete(categoryId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }
}
