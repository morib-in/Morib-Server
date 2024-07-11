package org.sopt.jaksim.task.dto;

import java.util.List;

public record StartTimerRequest(
        List<Long> taskIdList
) {
}
