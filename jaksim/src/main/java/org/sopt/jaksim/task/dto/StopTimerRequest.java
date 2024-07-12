package org.sopt.jaksim.task.dto;

import java.time.LocalDate;

public record StopTimerRequest(
        LocalDate targetDate,
        int elapsedTime
) {
}
