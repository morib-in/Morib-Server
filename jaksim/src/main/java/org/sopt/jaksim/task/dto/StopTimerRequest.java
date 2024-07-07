package org.sopt.jaksim.task.dto;

public record StopTimerRequest(
        String targetDate,
        int elapsedTime
) {
}
