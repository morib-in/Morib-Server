package org.sopt.jaksim.category.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.category.dto.*;
import org.sopt.jaksim.category.service.CategoryService;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.domain.Mset;
import org.sopt.jaksim.mset.domain.TaskMset;
import org.sopt.jaksim.mset.service.CategoryMsetService;
import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.domain.TaskTimer;
import org.sopt.jaksim.task.service.TaskService;
import org.sopt.jaksim.task.service.TaskTimerService;
import org.sopt.jaksim.user.domain.User;
import org.sopt.jaksim.user.facade.UserFacade;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CategoryTaskFacade {
    private final UserFacade userFacade;
    private final TaskService taskService;
    private final CategoryService categoryService;
    private final TaskTimerService taskTimerService;

    public List<FilteredResourceResponse> getAllResources(LocalDate startDate, LocalDate endDate) {
//        User user = userFacade.getUserByPrincipal();
        // 구간에 맞는 Category 조회
        List<CategoryCheckResponse> categoryCheckResponseList = categoryService.getCategoriesInRange(3L, startDate, endDate);
        // 카테고리들로 CategoryTask 조회 + 카테고리에 task 엮기, CategoryTaskLink 안의 TaskWithTaskTimer에 timer 연결 안되어있는 상태
        List<CategoryTaskLink> categoryTaskLinkList = categoryService.getCategoryTaskByCategories(categoryCheckResponseList);
        // 날짜별로 데이터 만들기
        LocalDate idxDate = startDate;
        LocalDate limitDate = endDate.plusDays(1);
        List<FilteredResourceResponse> res = new ArrayList<>();
        while (!idxDate.isEqual(limitDate)) {
            // 날짜 생성
            FilteredResourceResponse target = FilteredResourceResponse.init(idxDate);
            // 구간에 해당하는 모든 CategoryTaskLink를 for-each loop
            for (CategoryTaskLink categoryTaskLink : categoryTaskLinkList) {
                // Category가 idxDate에 해당하는 데이터인지 (task는 category의 범위를 벗어날 수 없음, 무조건 포함됨)
                if (categoryService.isContains(categoryTaskLink.categoryCheckResponse(), idxDate)) {
                    // idxDate에 해당하는 CategoryTaskLink를 만들기 위한 TaskWithTimer List
                    List<TaskWithTaskTimer> taskWithTaskTimerList = new ArrayList<>();
                    // 해당 카테고리에 연결된 task들 for-each loop
                    for (TaskWithTaskTimer task : categoryTaskLink.taskWithTaskTimerList()) {
                        // taskList 중, idxDate에 해당하면 Task List에 추가
                        if (taskService.isContains(task, idxDate)) {
                            // targetTime을 가져와서 세팅
                            taskWithTaskTimerList.add(TaskWithTaskTimer.ofByInit(task, taskTimerService.getTaskTimeByTaskId(3L, idxDate, task.id())));
                        }
                    }
                    target.categoryTaskLinkList().add(CategoryTaskLink.of(categoryTaskLink.categoryCheckResponse(), taskWithTaskTimerList));
                }
            }
            res.add(target);
            idxDate = idxDate.plusDays(1);
        }
        return res;
    }

}
