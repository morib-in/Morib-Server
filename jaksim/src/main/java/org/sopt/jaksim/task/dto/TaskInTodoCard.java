package org.sopt.jaksim.task.dto;

import org.sopt.jaksim.task.domain.Task;
import org.sopt.jaksim.task.domain.TaskTimer;

import java.time.LocalDate;

public record TaskInTodoCard(
        String name,
        LocalDate startDate,
        LocalDate endDate,
        boolean isComplete,
        LocalDate targetDate,
        int targetTime
) {
    public static TaskInTodoCard of(Task task, TaskTimer taskTimer) {
        return new TaskInTodoCard(
                task.getName(),
                task.getStartDate(),
                task.getEndDate(),
                task.getIsComplete(),
                taskTimer.getTargetDate(),
                taskTimer.getTargetTime());
    }
}
