package org.sopt.jaksim.task.dto;

import java.time.LocalDate;

public record TotalTimeTodayResponse(
        LocalDate targetDate,
        int targetTime
) {
    public static TotalTimeTodayResponse of(LocalDate targetDate, int targetTime) {
        return new TotalTimeTodayResponse(targetDate, targetTime);
    }
}
