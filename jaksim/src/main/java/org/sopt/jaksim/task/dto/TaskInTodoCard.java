package org.sopt.jaksim.task.dto;

import org.sopt.jaksim.category.domain.Category;
import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.domain.TaskTimer;

import java.time.LocalDate;

public record TaskInTodoCard(
        Long id,
        String categoryName,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        boolean isComplete,
        LocalDate targetDate,
        int targetTime,
        int taskOrder
) {
    public static TaskInTodoCard of(Task task, TaskTimer taskTimer, Category category, int taskOrder) {
        return new TaskInTodoCard(
                task.getId(),
                category.getName(),
                task.getName(),
                task.getStartDate(),
                task.getEndDate(),
                task.getIsComplete(),
                taskTimer.getTargetDate(),
                taskTimer.getTargetTime(),
                taskOrder
                );
    }
}
