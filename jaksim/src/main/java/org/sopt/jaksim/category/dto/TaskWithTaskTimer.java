package org.sopt.jaksim.category.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Setter;

import java.time.LocalDate;

public record TaskWithTaskTimer(
        Long id,
        String name,
        LocalDate startDate,
        LocalDate endDate,
        int targetTime
) {
    public static TaskWithTaskTimer of(Long id, String name, LocalDate startDate, LocalDate endDate, int targetTime) {
        return new TaskWithTaskTimer(id, name, startDate, endDate, targetTime);
    }
    public static TaskWithTaskTimer init(Long id, String name, LocalDate startDate, LocalDate endDate) {
        return new TaskWithTaskTimer(id, name, startDate, endDate, 0);
    }
    public static TaskWithTaskTimer ofByInit(TaskWithTaskTimer taskWithTaskTimer, int targetTime) {
        return new TaskWithTaskTimer(taskWithTaskTimer.id(), taskWithTaskTimer.name(), taskWithTaskTimer.startDate, taskWithTaskTimer.endDate, targetTime);
    }
}
