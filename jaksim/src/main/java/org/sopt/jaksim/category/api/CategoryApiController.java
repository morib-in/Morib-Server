package org.sopt.jaksim.category.api;


import lombok.RequiredArgsConstructor;
import org.sopt.jaksim.category.dto.CategoryCreateRequest;
import org.sopt.jaksim.category.service.CategoryService;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.message.SuccessMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping("/categories")
    public ResponseEntity<BaseResponse<?>> create(@RequestBody CategoryCreateRequest request) {
        categoryService.create(request);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS);
    }
}
