package org.sopt.jaksim.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.category.dto.CategoryCreateRequest;
import org.sopt.jaksim.category.repository.CategoryRepository;
import org.sopt.jaksim.mset.service.MsetService;
import org.sopt.jaksim.user.facade.UserFacade;
import org.springframework.stereotype.Service;


@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final UserFacade userFacade;
    private final MsetService msetService;

    public void create(CategoryCreateRequest categoryCreateRequest) {
        Category category = Category.create(
                categoryCreateRequest.name(),
                userFacade.getUserByPrincipal().getId(),
                categoryCreateRequest.startDate(),
                categoryCreateRequest.endDate());
        category = categoryRepository.save(category);
        msetService.createByCategory(categoryCreateRequest, category.getId());
    }
}
