package org.sopt.jaksim.category.api;


import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.category.dto.CategoryCreateRequest;
import org.sopt.jaksim.category.dto.FilteredResourceResponse;
import org.sopt.jaksim.category.service.CategoryService;
import org.sopt.jaksim.category.service.CategoryTaskFacade;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.message.SuccessMessage;

import org.sopt.jaksim.task.service.TaskService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryApiController {
    private final CategoryService categoryService;
    private final TaskService taskService;
    private final CategoryTaskFacade categoryTaskFacade;

    @PostMapping("/categories")
    public ResponseEntity<BaseResponse<?>> create(@RequestBody CategoryCreateRequest request) {
        categoryService.create(request);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }

    @GetMapping("/resources")
    public ResponseEntity<BaseResponse<?>> retrieve(@RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                    @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {
        List<FilteredResourceResponse> response = categoryTaskFacade.getAllResources(startDate, endDate);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
    }
}
