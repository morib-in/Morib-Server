package org.sopt.jaksim.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.CategoryTask;
import org.sopt.jaksim.category.repository.CategoryTaskRepository;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.task.domain.Task;

import org.sopt.jaksim.task.dto.TaskCreateRequest;
import org.sopt.jaksim.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryTaskRepository categoryTaskRepository;

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
    }

    public boolean isContains(Task task, LocalDate idxDate) {
        return task.getStartDate().equals(idxDate) || // startDate = idxDate
                task.getEndDate().equals(idxDate) || // endDate = idxDate
                (task.getStartDate().isBefore(idxDate) && task.getEndDate().isAfter(idxDate)); // startDate, endDate가 idxDate를 포함
    }
    public void create(Long categoryId, TaskCreateRequest taskCreateRequest) {
        Task task = Task.create(
                taskCreateRequest.name(),
                taskCreateRequest.startDate(),
                taskCreateRequest.endDate());

        taskRepository.save(task);

        CategoryTask categoryTask = CategoryTask.create(
                categoryId,
                task.getId());

        categoryTaskRepository.save(categoryTask);
    }
}
