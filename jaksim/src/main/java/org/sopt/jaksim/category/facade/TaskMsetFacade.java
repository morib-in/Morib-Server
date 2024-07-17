package org.sopt.jaksim.category.facade;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.category.dto.CategoryMsetLinkResponse;
import org.sopt.jaksim.category.dto.TaskMsetLinkResponse;
import org.sopt.jaksim.category.service.CategoryService;
import org.sopt.jaksim.mset.domain.CategoryMset;
import org.sopt.jaksim.mset.domain.Mset;
import org.sopt.jaksim.mset.domain.MsetDTO;
import org.sopt.jaksim.mset.domain.TaskMset;
import org.sopt.jaksim.mset.service.CategoryMsetService;
import org.sopt.jaksim.mset.service.MsetService;
import org.sopt.jaksim.mset.service.TaskMsetService;
import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.service.TaskService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskMsetFacade {
    private final TaskService taskService;
    private final MsetService msetService;
    private final TaskMsetService taskMsetService;

    public TaskMsetLinkResponse getFromOtherTask(Long taskId) {
        Task task = taskService.getTaskById(taskId);
        List<TaskMset> taskMsetList = taskMsetService.getByTaskId(taskId);
        List<MsetDTO> msets = taskMsetList.stream() //
                .map(taskMset -> msetService.getMsetById(taskMset.getMsetId()))
                .map(mset -> new MsetDTO(mset.getId(), mset.getName(), mset.getUrl()))
                .collect(Collectors.toList());
        return TaskMsetLinkResponse.of(task, msets);
    }

}
