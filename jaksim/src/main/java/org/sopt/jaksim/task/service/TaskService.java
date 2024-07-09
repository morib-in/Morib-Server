package org.sopt.jaksim.task.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.global.exception.NotFoundException;
import org.sopt.jaksim.global.message.ErrorMessage;
import org.sopt.jaksim.task.domain.Task;

import org.sopt.jaksim.task.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

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
}
