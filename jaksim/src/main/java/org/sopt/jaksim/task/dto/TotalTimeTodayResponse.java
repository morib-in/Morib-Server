package org.sopt.jaksim.task.dto;

public record TotalTimeTodayResponse(
        String targetDate,
        int targetTime
) {
    public static TotalTimeTodayResponse of(String targetDate, int targetTime) {
        return new TotalTimeTodayResponse(targetDate, targetTime);
    }
}
