package org.sopt.jaksim.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.time.LocalDate;

public record TaskWithTaskTimer(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        int targetTime,
        boolean isComplete
) {
    public static TaskWithTaskTimer of(Long id, String name, LocalDate startDate, LocalDate endDate, int targetTime, boolean isComplete) {
        return new TaskWithTaskTimer(id, name, startDate, endDate, targetTime, isComplete);
    }
    public static TaskWithTaskTimer init(Long id, String name, LocalDate startDate, LocalDate endDate, boolean isComplete) {
        return new TaskWithTaskTimer(id, name, startDate, endDate, 0, isComplete);
    }
    public static TaskWithTaskTimer ofByInit(TaskWithTaskTimer taskWithTaskTimer, int targetTime, boolean isComplete) {
        return new TaskWithTaskTimer(taskWithTaskTimer.id(), taskWithTaskTimer.name(), taskWithTaskTimer.startDate, taskWithTaskTimer.endDate, targetTime, isComplete);
    }
}
