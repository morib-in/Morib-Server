package org.sopt.jaksim.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.category.domain.CategoryTask;
import org.sopt.jaksim.category.dto.TaskWithTaskTimer;
import org.sopt.jaksim.category.repository.CategoryTaskRepository;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.mset.repository.TaskMsetRepository;
import org.sopt.jaksim.mset.service.MsetService;
import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.dto.TaskCreateRequest;
import org.sopt.jaksim.task.domain.TodoTask;
import org.sopt.jaksim.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;
    private final CategoryTaskRepository categoryTaskRepository;
    private final TaskMsetRepository taskMsetRepository;
    private final MsetService msetService;
    private final TaskService taskService;

    public Task getTaskById(Long taskId) {
        return taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
    }

    public boolean isContains(TaskWithTaskTimer taskWithTaskTimer, LocalDate idxDate) {
        return taskWithTaskTimer.startDate().equals(idxDate) || // startDate = idxDate
                taskWithTaskTimer.endDate().equals(idxDate) || // endDate = idxDate
                (taskWithTaskTimer.startDate().isBefore(idxDate) && taskWithTaskTimer.endDate().isAfter(idxDate)); // startDate, endDate가 idxDate를 포함
    }

    public void create(Long categoryId, TaskCreateRequest taskCreateRequest) {
        Task task = Task.create(
                taskCreateRequest.name(),
                taskCreateRequest.startDate(),
                taskCreateRequest.endDate());

        task = taskRepository.save(task);

        CategoryTask categoryTask = CategoryTask.create(
                categoryId,
                task.getId());

        categoryTaskRepository.save(categoryTask);
    }

    public void toggleTaskCompletionStatus(Long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(
                () -> new NotFoundException(ErrorMessage.NOT_FOUND)
        );
        task.setIsComplete(!task.getIsComplete());
        taskRepository.save(task);
    }

    public List<Task> getTasksByTodoTask(List<TodoTask> todoTaskList) {
        List<Long> taskIdList = todoTaskList.stream().map(TodoTask::getTaskId).collect(Collectors.toList());
        return taskRepository.findAllById(taskIdList);
    }
}