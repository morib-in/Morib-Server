package org.sopt.jaksim.category.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.category.dto.CategoryCheckResponse;
import org.sopt.jaksim.category.domain.CategoryTask;
import org.sopt.jaksim.category.dto.CategoryCreateRequest;
import org.sopt.jaksim.category.dto.CategoryTaskLink;
import org.sopt.jaksim.category.repository.CategoryRepository;
import org.sopt.jaksim.category.repository.CategoryTaskRepository;
import org.sopt.jaksim.global.common.DateUtil;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.mset.service.MsetService;

import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.repository.TaskRepository;
import org.sopt.jaksim.task.service.TaskService;
import org.sopt.jaksim.user.domain.User;
import org.sopt.jaksim.user.facade.UserFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final CategoryTaskRepository categoryTaskRepository;
    private final TaskRepository taskRepository;
    private final UserFacade userFacade;
    private final MsetService msetService;
    private final TaskService taskService;

    public void create(CategoryCreateRequest categoryCreateRequest) {
        Category category = Category.create(
                categoryCreateRequest.name(),
                userFacade.getUserByPrincipal().getId(),
                DateUtil.stringToDate(categoryCreateRequest.startDate()),
                DateUtil.stringToDate(categoryCreateRequest.endDate()));
        category = categoryRepository.save(category);
        msetService.createByCategory(categoryCreateRequest, category.getId());
    }

    // 유저의 카테고리를 구간에 맞게 가져옴
    public List<Category> getCategoriesInRange(Long userId, LocalDate startDate, LocalDate endDate) {
        return categoryRepository.findByUserIdWithRange(userId, startDate, endDate);
    }

    public Category getCategoryById(Long categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
    }

    public List<CategoryTaskLink> getCategoryTaskByCategories(List<Category> categoryList) {
        List<CategoryTaskLink> categoryTaskLinkList = new ArrayList<>();
        for (Category category : categoryList) {
            // category에 대한 categoryTask 조회
            List<CategoryTask> categoryTaskList = categoryTaskRepository.findByCategoryId(category.getId());
            List<Task> taskList = new ArrayList<>();
            // 해당 category에 연결된 categoryTask들의 task들을 조회해서 categoryTaskLinkList에 주입
            for (CategoryTask categoryTask : categoryTaskList) {
                // 해당 categoryTask의 taskId로 task를 조회해서 taskList에 주입
                taskList.add(taskService.getTaskById(categoryTask.getTaskId()));
            }
            categoryTaskLinkList.add(CategoryTaskLink.of(category,taskList));
        }
        return categoryTaskLinkList;
    }

    public boolean isContains(Category category, LocalDate idxDate) {
        return (category.getStartDate().isBefore(idxDate) &&
                category.getEndDate().isAfter(idxDate)) ||
                category.getStartDate().equals(idxDate) ||
                category.getEndDate().equals(idxDate);
    }


    public List<CategoryCheckResponse> getCategoriesByUserId() {
        // userId -> user pk -> Long -> SecurityContextHolder Authentication 객체
        // principal handler
        Long userId = userFacade.getUserByPrincipal().getId();
        List<Category> categories = categoryRepository.findByUserId(userId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
        return categories.stream() //리스트를 스트림으로 변환
                //각 category 객체를 CategoryCheckResponse 객체로 변환
                .map(category -> CategoryCheckResponse.of(category.getId(), category.getName(), category.getStartDate(), category.getEndDate()))
                .collect(Collectors.toList()); //변환된 스트림을 리스트로 수집
    }
}

