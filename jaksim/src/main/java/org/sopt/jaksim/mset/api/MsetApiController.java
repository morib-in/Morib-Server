package org.sopt.jaksim.mset.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.Response;
import org.sopt.jaksim.category.dto.CategoryMsetLinkResponse;
import org.sopt.jaksim.category.facade.CategoryMsetFacade;
import org.sopt.jaksim.global.common.ApiResponseUtil;
import org.sopt.jaksim.global.common.BaseResponse;
import org.sopt.jaksim.global.message.SuccessMessage;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MsetApiController {
    private final CategoryMsetFacade categoryMsetFacade;

    @GetMapping("/mset/categories/{categoryId}")
    public ResponseEntity<BaseResponse<?>> getFromOtherCategory(@PathVariable("categoryId") Long categoryId) {
        CategoryMsetLinkResponse response = categoryMsetFacade.getFromOtherCategory(categoryId);
        return ApiResponseUtil.success(SuccessMessage.SUCCESS, response);
    }
}